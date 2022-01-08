package com.example.pingduoduo.enity.bo;

public class CourseQueryBo {
    private String category;
    private String college;
    private String status;
    private String courseName;
    private String teacherName;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public CourseQueryBo() {
    }

    public CourseQueryBo(String category, String college, String status, String courseName, String teacherName) {
        this.category = category;
        this.college = college;
        this.status = status;
        this.courseName = courseName;
        this.teacherName = teacherName;
    }
}
