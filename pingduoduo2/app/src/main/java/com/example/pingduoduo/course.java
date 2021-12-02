package com.example.pingduoduo;


public class course {
    String courseName;//课程名字
    String teacherName;//教师名字
    String  category;//类别
    String credit;//学分
    String  college;//开设学院


    public course(String courseName, String teacherName, String category, String credit, String college) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.category = category;
        this.credit = credit;
        this.college = college;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


}
