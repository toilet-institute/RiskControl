package com.jxlt.udic.riskcontrol.website.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求响应码及msg
 * 主要都是描述错误信息的
 * 不要用200，RootResult默认2000为成功
 * @date 2018/11/19 17:47
 * @author
 */
@AllArgsConstructor
@Getter
public enum ResponseResultCodeEnum {

    /*通用成功*/
    SUCCESS(2000, "成功"),

    /*通用系统异常*/
    ERROR(4000, "系统出现异常"),
    /*请求参数异常*/
    REQUEST_PARAMS_ERROR(4001, "请求参数异常"),

    /*添加记录异常*/
    GET_RECORD_ERROR(4002, "获取记录出现异常"),
    /*添加记录异常*/
    ADD_RECORD_ERROR(4003, "添加记录出现异常"),
    /*更新记录异常*/
    UPDATE_RECORD_ERROR(4004, "更新记录出现异常"),


    /*用户未登录*/
    USER_VALIDATE_ERROR(4005, "用户未登录"),
    /*用户未登录*/
    USER_NOT_FOUND_ERROR(4006, "用户不存在"),
    /*通用接口调用失败*/
    API_POST_ERROR(4400, "接口调用失败"),
    /*云门户接口调用失败*/
    CLOUD_POST_ERROR(4401, "云门户接口调用失败"),
	/*短信接口调用失败*/
    SMS_POST_ERROR(4402, "短信接口调用失败");
	
    /**
     * 结果编码
     */
    private int code;
    /**
     * 结果消息
     */
    private String msg;

}
