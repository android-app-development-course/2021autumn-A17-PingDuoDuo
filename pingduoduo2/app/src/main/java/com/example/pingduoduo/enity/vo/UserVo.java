package com.example.pingduoduo.enity.vo;


public class UserVo {

    private Long id;
    private String name;


    private Integer gender;

    private String campus;

    private String college;

    public UserVo(Long id, String name, Integer gender, String campus, String college, String campus1, String college1) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.campus = campus;
        this.college = college;
        this.campus = campus1;
        this.college = college1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public UserVo() {
    }

    public UserVo(Long id, String name, Integer gender, String campus, String college) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.campus = campus;
        this.college = college;
    }


}
