package com.jxlt.udic.riskcontrol.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 参数校验配置
 *  用来处理参数比较少，不封装成bean的情况
 *
 * @author NCPLT
 * @create 2018-11-20 20:15
 **/
public class ValidationConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
