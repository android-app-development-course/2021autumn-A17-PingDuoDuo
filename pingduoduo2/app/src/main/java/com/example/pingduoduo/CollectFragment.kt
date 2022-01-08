package com.example.pingduoduo


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.JsonWriter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pingduoduo.common.Global
import com.example.pingduoduo.enity.Course
import com.example.pingduoduo.enity.bo.CourseQueryBo
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.android.synthetic.main.coursename_frag.*
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import kotlin.concurrent.thread
import com.example.pingduoduo.conditionMenuActivity as conditionMenuActivity


class CollectFragment:Fragment() {


    private  var courseList=ArrayList<Course>()
    var cate=""
    var col=""
    var stat=""
    var t_name=""
    var c_name=""
    var userId = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.coursename_frag,container,false)
//        (conditionMenuActivity)context

//        cate=activity?.intent?.getStringExtra("category").toString()
//        Log.i("123", s.toString())
//        cate= !!
//         col= bundle.getString("college")!!
        if(isAdded)
        {
            var bundle =this.getArguments()
            cate=bundle?.getString("category").toString()
            col=bundle?.getString("college").toString()
            stat=bundle?.getString("status").toString()
            t_name=bundle?.getString("teachername").toString()
            c_name=bundle?.getString("coursename").toString()
            userId= bundle?.getString("userId").toString()
            Log.i("userId", userId)
        }else
        {
            Log.i("asdasd","1234")
        }
        return  view
    }
    inner  class courseAdapter(val  clist:List<Course>):
        RecyclerView.Adapter<courseAdapter.ViewHolder>(){
        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val cname:TextView=view.findViewById(R.id.cname)
            val tname:TextView=view.findViewById(R.id.tname)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.course_item,parent,false)
            val holder= ViewHolder(view)

            holder.itemView.setOnClickListener {
                val course=clist[holder.adapterPosition]
                Global.course.category=course.category

                Global.course.courseName=course.courseName

                Global.course.teacherName=course.teacherName

                Global.course.credits=course.credits

                Global.course.college=course.college

                Global.course.id=course.id



                Toast.makeText(parent.context,course.courseName+course.teacherName+course.category+course.credits +course.college,Toast.LENGTH_LONG).show()
                courseContentActivity2.actionStart(parent.context,course.courseName,course.teacherName,course.category,course.credits,course.college,course.id)
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val course = clist[position]
            holder.cname.text = course.courseName
            holder.tname.text = course.teacherName
        }
        override fun getItemCount(): Int {
            return clist.size
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        courseItemRecycleView.layoutManager=LinearLayoutManager(activity)
        val adapter=courseAdapter(courseList)


        courseItemRecycleView.adapter=adapter
        refreshLayout.setOnRefreshListener {
            refreshLayout.finishRefresh(500 /*,false*/) //传入false表示刷新失败

//            courseList.clear()
            init()


        }
        refreshLayout.setOnLoadMoreListener {
            refreshLayout.finishLoadMore(500/*,false*/);
            init()
//            adapter.notifyDataSetChanged()

        }
    }

    fun init()
    {

        thread {
            val gson= Gson()
            val  client= OkHttpClient()
//            bundle.putString("category",courseQueryBo.getCategory());
//            bundle.putString("college",courseQueryBo.getCollege());
//            bundle.putString("status",courseQueryBo.getCollege());



//            var json="{"+"}"
            var c=CourseQueryBo()
            c.category=cate;
            c.college=col
            c.courseName=c_name
            c.teacherName=t_name
            c.status=stat

            var json = gson.toJson(c)
            Log.i("1234",json)
            var requestBody = FormBody.Builder().build();


            var urlBuilder = (Global.url+"/course/getCollectedList").toHttpUrlOrNull()
                ?.newBuilder()
            urlBuilder?.addQueryParameter("uid",userId)
            var request = Request.Builder().url(urlBuilder!!.build()).build()
            /*var build = okhttp3.Request.Builder().url(Global.url+"/course/getCollectedList")
                .post(RequestBody.create(MediaType.let { "application/json".toMediaTypeOrNull() },json)).build()*/

            val response=client.newCall(request).execute();
            var result=response.body?.string()
            var j=JSONObject(result)
//            var resultList=JSONArray(j.getString("data"))
            var resultList = j.getString("data")

//            Log.i("1111111111",resultList.toString())


            val typeOf=object: TypeToken<ArrayList<Course>>(){}.type
            var courseList2=gson.fromJson<ArrayList<Course>>(resultList,typeOf)
            Log.i("111111",courseList2.size.toString())
            showResponse(courseList2)
            //            val gson= Gson()
////            val typeOf=object: TypeToken<ArrayList<Course>>(){}.type
////            courseList=gson.fromJson<ArrayList<Course>>(resultList,typeOf)
////            Log.i("111111",courseList.scize.toString())
        }



    }

    private fun showResponse(resultList:ArrayList<Course>) {
        activity?.runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            courseList=resultList
            courseItemRecycleView.layoutManager=LinearLayoutManager(activity)
            val adapter=courseAdapter(courseList)


            courseItemRecycleView.adapter=adapter
        }
    }



}