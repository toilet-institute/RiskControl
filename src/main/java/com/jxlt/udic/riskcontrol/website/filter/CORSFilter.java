package com.jxlt.udic.riskcontrol.website.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

    public CORSFilter() {        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        /*该字段是必须的。它的值要么是请求时Origin字段的值，要么是一个*，表示接受任意域名的请求。*/
        String header = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Origin","*");
		/* 该字段可选。它的值是一个布尔值，表示是否允许发送Cookie。默认情况下，
		 * Cookie不包括在CORS请求之中。设为true，即表示服务器明确许可，Cookie可以包含在请求中，一起发给服务器。这个值也只能设为true，如果服务器不要浏览器发送Cookie，删除该字段即可。
		*/        
        response.setHeader("Access-Control-Allow-Credentials", "true");
        /*该字段必需，它的值是逗号分隔的一个字符串，表明服务器支持的所有跨域请求的方法。
                      注意，返回的是所有支持的方法，而不单是浏览器请求的那个方法。这是为了避免多次"预检"请求。*/
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
       /* 该字段可选，用来指定本次预检请求的有效期，单位为秒。*/
        response.setHeader("Access-Control-Max-Age", "3600");
        /*如果浏览器请求包括Access-Control-Request-Headers字段，则Access-Control-Allow-Headers字段是必需的。它也是一个逗号分隔的字符串，
                       表明服务器支持的所有头信息字段，不限于浏览器在"预检"中请求的字段。*/
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me,Authorization");

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}