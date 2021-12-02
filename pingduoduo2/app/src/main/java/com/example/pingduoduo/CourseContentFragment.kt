package com.example.pingduoduo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.course_content_fragment.*
import kotlin.math.log

class CourseContentFragment:Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.course_content_fragment,container,false)

    }

    fun refresh(cName:String,t: String,category: String,credit:String,college:String){
        contentLayout.visibility=View.VISIBLE
        teacherContent.text=t

        courseName.text=cName
        categoryContent.text=category
        creditContent.text=credit
        collegeContent.text=college

    }
}

