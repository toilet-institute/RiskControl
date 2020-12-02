package com.jxlt.udic.riskcontrol.swagger2.dto;

import lombok.Data;

/**
 * Created by XiuYin.Cui on 2018/1/11.
 */
@Data
public class Response {
    /**
     * 返回参数
     */
    private String description;

    /**
     * 参数名
     */
    private String name;

    /**
     * 说明
     */
    private String remark;


    public  Response(){

    }

    public Response(String description, String name, String remark) {
        this.description = description;
        this.name = name;
        this.remark = remark;
    }
}
