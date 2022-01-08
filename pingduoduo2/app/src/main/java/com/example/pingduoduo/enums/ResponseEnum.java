package com.example.pingduoduo.enums;


public enum  ResponseEnum {
    ERROR(-1, "服务端错误"),
    SUCCESS(200, "成功"),

    PASSWORD_ERROR(1,"密码错误"),

    USERNAME_EXIST(2, "用户不存在"),

    PINGKE_ERROR(5,"失败");

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    //状态码
    Integer code;

    //描述
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
