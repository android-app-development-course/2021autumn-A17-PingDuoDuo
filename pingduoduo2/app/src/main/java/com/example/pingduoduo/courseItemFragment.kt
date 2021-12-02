package com.example.pingduoduo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coursename_frag.*


class courseItemFragment:Fragment() {
    private  var courseList=ArrayList<course>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.coursename_frag,container,false)
    }
    inner  class courseAdapter(val  clist:List<course>):
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

                Toast.makeText(parent.context,course.courseName+course.teacherName+course.category+course.credit+course.college,Toast.LENGTH_LONG).show()
                courseContentActivity2.actionStart(parent.context,course.courseName,course.teacherName,course.category,course.credit,course.college)
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
            refreshLayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败

//            courseList.clear()
//            init()
//           adapter.notifyDataSetChanged()
        }
        refreshLayout.setOnLoadMoreListener {
            init()
            adapter.notifyDataSetChanged()
            refreshLayout.finishLoadMore(2000/*,false*/);
        }
    }

    fun init()
    {
//        public course(String courseName, String teacherName, String category, String credit, String college) {
            repeat(10){
            courseList.add(course("编译原理","黄煜廉","必修","2","计算机"))
            courseList.add(course("数据结构","黄华毅","必修","2","计算机"))
            courseList.add(course("有机化学","陶静奇","必修","2","化学"))
                courseList.add(course("面向对象程序设计","曹阳","必修","2","计算机"))
                courseList.add(course("移动应用开发","曹阳","必修","2","计算机"))
                courseList.add(course("软件工程","刘宇东","必修","2","化学"))
        }
    }

}