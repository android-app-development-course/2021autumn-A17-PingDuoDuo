package com.example.pingduoduo.enity;

public class Evaluation {
    private Long id;
    private Long cId;//课程id
    private String evaluator;//评价者
    private String checkIn;//考勤频率
    private String mark;//期末打分
    private String evaMode;//考核方式
    private String comment;//评价内容
    private Integer likes;//点赞数量
    private String status;//审核状态
    private String evaLevel;//综合评分


    public Evaluation(Long id, Long cId, String evaluator, String checkIn, String mark, String evaMode, String comment, Integer likes, String status) {
        this.id = id;
        this.cId = cId;
        this.evaluator = evaluator;
        this.checkIn = checkIn;
        this.mark = mark;
        this.evaMode = evaMode;
        this.comment = comment;
        this.likes = likes;
        this.status = status;
    }

    public String getEvaLevel() {
        return evaLevel;
    }

    public void setEvaLevel(String evaLevel) {
        this.evaLevel = evaLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
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

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
