package com.jxlt.udic.riskcontrol.website.config;

import java.util.Map;

import com.jxlt.udic.riskcontrol.website.filter.PermissionFilter;
import com.jxlt.udic.riskcontrol.website.filter.RequestFilter;
import com.jxlt.udic.riskcontrol.website.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.google.common.collect.Maps;

/**
 * SpringMVC 配置类
 *
 * @author NCPLT
 * @create 2017/6/20
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${allowed-uris}")
	private String allowedUris;
	@Value("${url-permission.allowed-uris}")
	private String permissAlowedUris;
	@Value("${url-permission.enable}")
	private Boolean urlPermissionEnable;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new
		// RepeatedlyReadInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
//		super.addInterceptors(registry);
	}

	@Bean("requestFilter")
	@Order(0)
	public FilterRegistrationBean requestFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		RequestFilter requestFilter = new RequestFilter();
		registration.setFilter(requestFilter);
		registration.addUrlPatterns("/*");
		Map<String, String> params = Maps.newHashMap();
		params.put("allowedUris", allowedUris);
		registration.setInitParameters(params);
		return registration;
	}

	@Bean("permissionFilter")
	@Order(1)
	public FilterRegistrationBean permissionFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		PermissionFilter permissionFilter = new PermissionFilter();
		registration.setFilter(permissionFilter);
		registration.addUrlPatterns("/*");
		Map<String, String> params = Maps.newHashMap();
		params.put("allowedUris", allowedUris+","+permissAlowedUris);
		params.put("urlPermissionEnable", urlPermissionEnable.toString());
		registration.setInitParameters(params);
		return registration;
	}
	/*
	 * @Override public void
	 * configureHandlerExceptionResolvers(List<HandlerExceptionResolver>
	 * exceptionResolvers) {
	 * super.configureHandlerExceptionResolvers(exceptionResolvers);
	 * exceptionResolvers.add(0, new DataaccessExceptionHandler()); }
	 */
}
