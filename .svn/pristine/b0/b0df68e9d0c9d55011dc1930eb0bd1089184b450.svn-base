package com.jxlt.udic.riskcontrol.website.config;

import java.util.Properties;

import com.jxlt.udic.riskcontrol.website.util.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MyBatisMapperScannerConfig implements ApplicationContextAware{
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//增加ApplicationContextAware实现的目的是为了保证SpringUtil能优先初始化。
    	new SpringUtil().setApplicationContext(applicationContext);
    	
    }
	
    @Bean
    @Primary
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	Properties properties=new Properties();
    	properties.put("mappers","tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        String baseMapperPackage = SpringUtil.getCfgProperty("mybatis.base-mapper-package");
        mapperScannerConfigurer.getMapperHelper().setProperties(properties);
        mapperScannerConfigurer.setBasePackage(baseMapperPackage);
        return mapperScannerConfigurer;
    }
}
