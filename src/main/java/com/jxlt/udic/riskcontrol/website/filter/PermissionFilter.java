package com.jxlt.udic.riskcontrol.website.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

//import com.wxapp.ncplt.domain.SysMenu;

//import com.wxapp.ncplt.service.SysmenuService;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 作者 NCPLT
 * @version 创建时间：2018年12月4日 上午9:34:19 类说明 该过滤器的顺序应该在loginFilter之后
 */
@Slf4j
public class PermissionFilter implements Filter {
	private Set<String> allowedUris;
	
	private boolean isEnable;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String[] uriArray = StringUtils.split(filterConfig.getInitParameter("allowedUris"), ",");
		HashSet<String> uriSet = new HashSet<>();
		if (ArrayUtils.isNotEmpty(uriArray)) {
			Arrays.stream(uriArray).forEach(e -> uriSet.add(StringUtils.trim(e)));
		}
		allowedUris = uriSet;
		isEnable=Boolean.valueOf(filterConfig.getInitParameter("urlPermissionEnable"));
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestURI = httpRequest.getRequestURI();
		//如果没有启用，直接通过
		if(!isEnable) {
			chain.doFilter(request, response);
			return;
		}
		// 如果是根路径，直接通过
		String contextPath = httpRequest.getContextPath() + "/";
		if (StringUtils.equals(contextPath, requestURI)) {
			chain.doFilter(request, response);
			return;
		}
		// 判断是否是允许通过的路径
		boolean isAllowUrl = allowedUris.stream().filter(requestURI::contains).findFirst().isPresent();
		if (isAllowUrl) {
			chain.doFilter(request, response);
			return;
		}
//		// 进行权限校验,
//		List<SysMenu> menuList = SessionContent.getUserMenuList(httpRequest);
//		// 先暂时以包含为主
//		boolean hasPermission = menuList.stream().filter(item -> {
//			if (StringUtils.isEmpty(item.getLinkurl())) {
//				return false;
//			}
//			String[] permissionUrls = StringUtils.split(item.getLinkurl(), ",");
//			return Arrays.stream(permissionUrls).filter(requestURI::contains).findFirst().isPresent();
//		}).findFirst().isPresent();
//		if (hasPermission) {
//			chain.doFilter(request, response);
//			return;
//		}
//		httpResponse.reset();
//		httpResponse.setContentType("text/json;charset=UTF-8");
//		httpResponse.getWriter().write(JSON.toJSONString(RootResult.error(-1, "用户没有权限")));
//		log.info("{}没有对于url:{}的权限",SessionContent.getLoginUser(httpRequest).getId(),requestURI);
	}

	@Override
	public void destroy() {

	}

}
