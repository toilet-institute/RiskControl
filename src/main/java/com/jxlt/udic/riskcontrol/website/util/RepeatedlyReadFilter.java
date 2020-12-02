package com.jxlt.udic.riskcontrol.website.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 复制请求数据包body
 * 以提供 拦截器下 可数次获取Body数据包
 * @author NCPLT-2020
 * @version 1.0.0
 * @date 2017/09/21
 */
public class RepeatedlyReadFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RepeatedlyReadFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("复制request.getInputStream流");
        ServletRequest requestWrapper = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new RepeatedlyReadRequestWrapper(httpRequest);
            String preurl = httpRequest.getContextPath();
            String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
    		if (url.toLowerCase().indexOf("realtimedownservice") <0 ) {
    			String soap = "<?xml version='1.0' encoding='UTF-8'?>";
    			try {
   				 	soap += getBodyString(requestWrapper);
   				 	logger.info("请求Body: {} ", soap);
   		            MessageFactory msgFactory;
   		            msgFactory = MessageFactory.newInstance();
   		            SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soap.getBytes("UTF-8")));
   		            reqMsg.saveChanges();
   		            SOAPBody body = reqMsg.getSOAPBody();
   		            Iterator<SOAPElement> iterator = body.getChildElements();
   		        } catch (Exception e) {
   		            e.printStackTrace();
   		            httpResponse.sendRedirect("loginerror");
   		        }
    		}
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {

    }
    
    /**
     * 获取请求Body
     *
     * @param request
     *
     * @return
     */
    public static String getBodyString(final ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
            sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
        return sb.toString();
    }

    /**
     * Description: 复制输入流</br>
     *
     * @param inputStream
     *
     * @return</br>
     */
    public static InputStream cloneInputStream(ServletInputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1) {
            byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }
}
