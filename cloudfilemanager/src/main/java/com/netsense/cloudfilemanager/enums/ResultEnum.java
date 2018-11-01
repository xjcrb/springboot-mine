package com.netsense.cloudfilemanager.enums;

public enum ResultEnum {

    PRIMARY_SCHOOL(100,"我猜你可能是在上小学"),
    MIDDLE_SCHOOL(101,"我猜你可能是在上中学"),
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(100,"成功"),
    authfaild(101,"输入账号或者密码错误"),
    inputnull(102,"输入账号或者密码为空"),
    FAILD(104,"失败"),
    PASSWORDREPEAT(105,"输入的新密码跟旧密码一样"),


    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
