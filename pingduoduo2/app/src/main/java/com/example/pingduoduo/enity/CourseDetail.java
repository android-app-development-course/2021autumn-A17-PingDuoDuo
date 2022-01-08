package com.example.pingduoduo.enity;

import android.view.View;

public class CourseDetail {
    private String checkIn="";//考勤频率
    private String mark="";//期末打分
    private String evaMode="";//考核方式
    private String comment="";//评价内容
    private String cid;//课程id
    private  String evaLevel;//综合打分

    public String getEvaLevel() {
        return evaLevel;
    }

    public void setEvaLevel(String evaLevel) {
        this.evaLevel = evaLevel;
    }

    public String getcId() {
        return cid;
    }

    public void setcId(String cId) {
        this.cid = cId;
    }




    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getEvaMode() {
        return evaMode;
    }

    public void setEvaMode(String evaMode) {
        this.evaMode = evaMode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }




    public void setComment(View viewById) {
    }
}
