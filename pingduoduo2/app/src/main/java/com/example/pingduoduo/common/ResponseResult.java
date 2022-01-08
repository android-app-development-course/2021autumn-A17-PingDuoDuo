package com.example.pingduoduo.common;



import com.example.pingduoduo.enums.ResponseEnum;

import java.util.Objects;


public class ResponseResult<T> {

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Integer status;
    private String msg;
    private T data;

    private ResponseResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private ResponseResult(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseResult<T> successByMsg(String msg) {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(),msg);
    }
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(),data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<T> error(ResponseEnum responseEnum) {
        return new ResponseResult<>(responseEnum.getCode(),responseEnum.getDesc());
    }
    public static <T> ResponseResult<T> error(ResponseEnum responseEnum,String msg) {
        return new ResponseResult<>(responseEnum.getCode(),msg);
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
