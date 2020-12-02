package com.jxlt.udic.riskcontrol.website.util;


import com.jxlt.udic.riskcontrol.website.enums.ResponseResultCodeEnum;
import com.jxlt.udic.riskcontrol.website.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
* 参数校验工具
* @create 2018-11-20 14:29
* @author NCPLT-2020
**/
@Slf4j
public class ParamValidUtil {

    public static void valid(BindingResult result){
        if(result.hasErrors()){
			for (ObjectError error : result.getAllErrors()) {
				log.error("参数错误：{}", error.getDefaultMessage());
				throw new BusinessException(ResponseResultCodeEnum.REQUEST_PARAMS_ERROR.getCode(), ResponseResultCodeEnum.REQUEST_PARAMS_ERROR.getMsg() + ":" + error.getDefaultMessage());
			}
		}
    }
}
