package com.example.pingduoduo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pingduoduo.enity.Evaluation

class EvaluationAdapter(val evaluationList: List<Evaluation>):
    RecyclerView.Adapter<EvaluationAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val attendance:TextView = view.findViewById(R.id.attendance)
        val attendanceContent:TextView = view.findViewById(R.id.attendanceContent)
        //val score:TextView = view.findViewById(R.id.score)
        val scoreContent:TextView = view.findViewById(R.id.scoreContent)
        //val testWay:TextView = view.findViewById(R.id.test_way)
        val testWayContent:TextView = view.findViewById(R.id.test_wayContent)
        val comment:TextView = view.findViewById(R.id.comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evalution_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evaluation = evaluationList[position]
        holder.attendanceContent.text = evaluation.checkIn
        holder.scoreContent.text = evaluation.mark
        holder.testWayContent.text = evaluation.evaMode
        holder.comment.text = evaluation.comment
    }

    override fun getItemCount() = evaluationList.size

}