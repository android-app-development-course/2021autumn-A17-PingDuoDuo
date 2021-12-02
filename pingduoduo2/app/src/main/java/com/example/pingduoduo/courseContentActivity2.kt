package com.example.pingduoduo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_course_content2.*
import kotlinx.android.synthetic.main.course_content_fragment.*
import kotlinx.android.synthetic.main.coursename_frag.*
import kotlinx.android.synthetic.main.coursename_frag.refreshLayout


class courseContentActivity2 : AppCompatActivity() {

    companion object{
        fun actionStart(context: Context, courseName:String, teacherName: String, category: String, credit:String, college:String){
            val intent= Intent(context,courseContentActivity2::class.java).apply {
                putExtra("course_Name",courseName)
                putExtra("teacher_Name",teacherName)
                putExtra("category_",category)
                putExtra("credit_",credit)
                putExtra("college_",college)
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
        if(courseName!=null&&teacherName!=null&&category!=null&&credit!=null&&college!=null)
        {
            val fragment=courseContent as CourseContentFragment
            fragment.refresh(courseName,teacherName,category,credit,college)
        }

        //动态添加按钮图案
        val buttonSave = findViewById<Button>(R.id.save)
        var iconSave = getDrawable(R.drawable.icon_save1)
        iconSave?.setBounds(0, 0, 70, 70)
        buttonSave.setCompoundDrawables(null, iconSave, null, null)

        val buttonShare = findViewById<Button>(R.id.share)
        val iconShare = getDrawable(R.drawable.icon_share4)
        iconShare?.setBounds(0, 0, 70, 70)
        buttonShare.setCompoundDrawables(null, iconShare, null, null)

        //点击“添加评论”跳转到对应界面
        val buttonAddComment = findViewById<Button>(R.id.addComment)
        buttonAddComment.setOnClickListener{
            val intent = Intent(this,course_detail::class.java)
            startActivity(intent)
        }

        refreshLayout.setOnRefreshListener {
            refreshLayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败

//            courseList.clear()
//            init()
//           adapter.notifyDataSetChanged()
        }


        buttonSave.setOnClickListener{
            iconSave = getDrawable(R.drawable.icon_save)
            iconSave?.setBounds(0, 0, 70, 70)
            buttonSave.setCompoundDrawables(null, iconSave, null, null)
            val toast = Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
