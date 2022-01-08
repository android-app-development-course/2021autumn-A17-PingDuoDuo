package com.example.pingduoduo.enity;


import org.jetbrains.annotations.NotNull;

public class Course {
     Long id;
    String courseName;//课程名字
    String teacherName;//教师名字
    String  category;//类别
    String credits;//学分
    String  college;//开设学院
    String eva_entire;//总体评价
    Integer eva_cnt;//评价总数
    String status;// 评价状态



    public Course(Long id, String courseName, String teacherName, String category, String credits, String college, String eva_entire, Integer eva_cnt, String status) {
        this.id = id;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.category = category;
        this.credits = credits;
        this.college = college;
        this.eva_entire = eva_entire;
        this.eva_cnt = eva_cnt;
        this.status = status;
    }

    public Course(@NotNull String s, @NotNull String s1, @NotNull String s2, @NotNull String s3, @NotNull String s4) {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Course() {
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEva_entire() {
        return eva_entire;
    }

    public void setEva_entire(String eva_entire) {
        this.eva_entire = eva_entire;
    }

    public Integer getEva_cnt() {
        return eva_cnt;
    }

    public void setEva_cnt(Integer eva_cnt) {
        this.eva_cnt = eva_cnt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
