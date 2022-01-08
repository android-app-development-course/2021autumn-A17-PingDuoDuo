package com.example.pingduoduo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pingduoduo.common.Global
import com.example.pingduoduo.enity.Course
import com.example.pingduoduo.enity.Evaluation
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_course_content2.*
import kotlinx.android.synthetic.main.course_content_fragment.*
import kotlinx.android.synthetic.main.coursename_frag.*
import kotlinx.android.synthetic.main.coursename_frag.refreshLayout
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.text.DecimalFormat
import kotlin.concurrent.thread


class courseContentActivity2 : AppCompatActivity() {
    private var evaluationList = ArrayList<Evaluation>()
    private var cid: String = ""
    private var userId: Long = 0
    private var haveCollected: Boolean = false

    companion object{
        fun actionStart(context: Context, courseName:String, teacherName: String, category: String, credit:String, college:String, id:Long){
            val intent= Intent(context,courseContentActivity2::class.java).apply {
                putExtra("course_Name",courseName)
                putExtra("teacher_Name",teacherName)
                putExtra("category_",category)
                putExtra("credit_",credit)
                putExtra("college_",college)
                putExtra("course_id",id.toString())
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_content2)
        val courseName=intent.getStringExtra("course_Name")
        val teacherName=intent.getStringExtra("teacher_Name")
        val category=intent.getStringExtra("category_")
        val credit=intent.getStringExtra("credit_")
        val college=intent.getStringExtra("college_")
        cid = intent.getStringExtra("course_id").toString()
        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        userId = preferences.getLong("userId",0)


        init()
        evaluationRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = EvaluationAdapter(evaluationList)
        evaluationRecyclerView.adapter = adapter
        if(courseName!=null&&teacherName!=null&&category!=null&&credit!=null&&college!=null)
        {
            val fragment=courseContent as CourseContentFragment
            fragment.refresh(courseName,teacherName,category,credit,college)

        }

        //动态添加按钮图案
        val buttonShare = findViewById<Button>(R.id.share)
        val iconShare = getDrawable(R.drawable.icon_share4)
        iconShare?.setBounds(0, 0, 70, 70)
        buttonShare.setCompoundDrawables(null, iconShare, null, null)

        //点击“添加评论”跳转到对应界面
        val buttonAddComment = findViewById<Button>(R.id.addComment)
        buttonAddComment.setOnClickListener{
            val intent = Intent(this,course_detail::class.java)
            intent.putExtra("cid",cid)
            startActivity(intent)
        }

        //刷新评论
        refreshLayout.setOnRefreshListener {
            init()
            refreshLayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败

        }

    }

    fun init() {

        thread {
            val client = OkHttpClient()
            var urlBuilder = (Global.url+"/evaluation/getEvaList").toHttpUrlOrNull()?.newBuilder()
            urlBuilder?.addQueryParameter("cid",cid)
            Log.i("cidddddd",cid)
            var request = Request.Builder().url(urlBuilder!!.build()).build()
            val response = client.newCall(request).execute()
            var responseData = response.body?.string()
            var j = JSONObject(responseData)
            //Log.i("1234",j.toString())
            var evaluationData = j.getString("data")
            //Log.i("12345",evaluationData)
            if (!evaluationData.equals("[]"))
            {
                val gson= Gson()
                val typeOf = object: TypeToken<ArrayList<Evaluation>>(){}.type
                var evaluations = gson.fromJson<ArrayList<Evaluation>>(evaluationData,typeOf)
                Log.i("7777777",evaluations.size.toString())
                showEvaResponse(evaluations)
            }

        }

        thread {
//            val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
//            userId = preferences.getLong("userId",0)
            val client = OkHttpClient()
            var urlBuilder = (Global.url+"/course/isCollected").toHttpUrlOrNull()?.newBuilder()
            urlBuilder?.addQueryParameter("cid",cid)
            urlBuilder?.addQueryParameter("uid", userId.toString())
//            Log.i("cidddddd",cid)
//            Log.i("uidddddddddd", userId.toString())
            var request = Request.Builder().url(urlBuilder!!.build()).build()
            val response = client.newCall(request).execute()
            var responseData = response.body?.string()
            var j = JSONObject(responseData)
            var collectionData = j.getString("data")
            when(collectionData){
                "false" -> haveCollected = false
                "true" -> haveCollected = true
            }
            showColResponse(haveCollected)
            Log.i("222222",collectionData)
            Log.i("999999", haveCollected.toString())
        }

//        evaluationList.add(Evaluation(1,1,"aa","经常","90","论文","老师人很好",1,"ac"))
//        evaluationList.add(Evaluation(1,1,"aa","经常","90","论文","老师人很好",1,"ac"))
//        showResponse(evaluationList)
    }

    private fun showEvaResponse(evaluations:ArrayList<Evaluation>){
        runOnUiThread {

            // 在这里进行UI操作，将结果显示到界面上
            evaluationList = evaluations
            Log.i("evaluationnnnnnn", evaluationList.toString())
            var result = 0.0
            for(evaluation in evaluationList) {
                result += evaluation.evaLevel.toDouble()
            }
            result /= evaluationList.size
            result = String.format("%.1f",result).toDouble()

            Log.i("ressssss",result.toString())
            grade.text = result.toString()
            evaluationRecyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = EvaluationAdapter(evaluationList)
            evaluationRecyclerView.adapter = adapter
        }
    }

    private fun showColResponse(colleted:Boolean) {
        runOnUiThread{
            val buttonSave = findViewById<Button>(R.id.save)
            var iconSave = getDrawable(R.drawable.icon_save1)
            when(haveCollected) {
                true -> iconSave = getDrawable(R.drawable.icon_save)
            }
            iconSave?.setBounds(0, 0, 70, 70)
            buttonSave.setCompoundDrawables(null, iconSave, null, null)

            //点击”收藏“状态变化
            buttonSave.setOnClickListener{
                when(haveCollected) {
                    true -> {
                        haveCollected = false
                        iconSave = getDrawable(R.drawable.icon_save1)

                        thread {
                            val client = OkHttpClient()
                            var urlBuilder = (Global.url+"/course/cancelCollect").toHttpUrlOrNull()?.newBuilder()
                            urlBuilder?.addQueryParameter("cid",cid)
                            urlBuilder?.addQueryParameter("uid", userId.toString())
                            var request = Request.Builder().url(urlBuilder!!.build()).build()
                            val response = client.newCall(request).execute()
                        }
                        Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show()
                    }

                    false -> {
                        haveCollected = true
                        iconSave = getDrawable(R.drawable.icon_save)

                        thread {
                            val client = OkHttpClient()
                            val requestBody = FormBody.Builder()
                                .add("cid",cid)
                                .add("uid", userId.toString())
                                .build()
                            Log.i("ciddddd",cid)
                            Log.i("uiddddd",userId.toString())
                            val request = Request.Builder()
                                .url(Global.url + "/course/collect")
                                .post(requestBody)
                                .build()
                            val response = client.newCall(request).execute()
                        }
                        Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show()
                    }
                }
                iconSave?.setBounds(0, 0, 70, 70)
                buttonSave.setCompoundDrawables(null, iconSave, null, null)

            }
        }
    }
}
