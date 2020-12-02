package com.jxlt.udic.riskcontrol.website.model;

import java.util.HashMap;
import java.util.Map;

import com.jxlt.udic.riskcontrol.website.enums.ResponseResultCodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "响应结果")
@Setter
@Getter
public class RootResult<T> {
	
	/**
	 * 返回标记 如果该标记为200则表明返回结果正确 否则都是错误消息
	 */
	@ApiModelProperty(value = "响应结果状态码")
	private int code = 200;

	/**
	 * 返回消息 一般用于返回错误描述或者操作提示
	 */
	@ApiModelProperty(value = "响应结果信息说明")
	private String msg = "ok";
	
	/**
	 * 返回标记 对象
	 */
	@ApiModelProperty(value = "响应结果集")
	private T data;

	public RootResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public RootResult() {
		Map map = new HashMap();	
		this.data = (T)map;
	}
	
	public RootResult(T data) {
		this.data = data;
	}

	public void setErrorInfo(ResponseResultCodeEnum enumObj) {
		this.setCode(enumObj.getCode());
		this.setMsg(enumObj.getMsg());
	}

	public static RootResult error(int code, String msg){
		return new RootResult(code, msg);
	}

	public static RootResult error(ResponseResultCodeEnum enumObj){
		return new RootResult(enumObj.getCode(), enumObj.getMsg());
	}
	
	public static <T> RootResult<T> success(T data){
		return new RootResult(data);
	}
}
