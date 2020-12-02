package com.jxlt.udic.riskcontrol.website.filter;

import com.jxlt.udic.riskcontrol.website.model.SysStaff;
import com.jxlt.udic.riskcontrol.website.service.SysStaffService;
import com.jxlt.udic.riskcontrol.website.model.input.SysStaffInputVO;
import com.jxlt.udic.riskcontrol.website.util.RequestHolder;
import com.jxlt.udic.riskcontrol.website.util.SessionContent;
import com.jxlt.udic.riskcontrol.website.model.RootResult;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.jxlt.udic.riskcontrol.website.util.JwtUtils;

import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 请求Filter
 * 判断session中是否有用户信息,如果没有返回字符串标识，主要解决页面没关闭时，服务器重启等情况
 *
 * @date:
 * @author: 2020
 */
@Slf4j
public class RequestFilter implements Filter {

    private Set<String> ALLOWED_URIS;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        RequestHolder.add(request);

        if (CollectionUtils.isEmpty(ALLOWED_URIS)) {
            chain.doFilter(req, res);
            return;
        }

        String requestURI = request.getRequestURI();

        //如果是根路径，直接通过
        String contextPath = request.getContextPath() + "/";
        if (StringUtils.equals(contextPath, requestURI)) {
            chain.doFilter(req, res);
            return;
        }

//        //判断用户Token
//        String header = request.getHeader("Token");
////        log.info(request.getParameter("logincode").toString());
////        SysStaff sysuser = userservice.getUserByLoginName(request.getParameter("logincode"));
////        SysStaff user = new ObjectMapper().readValue(request.getInputStream(), SysStaff.class);
//        if (header == null || !header.startsWith(JwtUtils.getAuthorizationHeaderPrefix())) {
//            log.error("Token为空或已失效 request：{}", request);
//            String token = Jwts.builder().setSubject("12345678")
//                    .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//                    .signWith(SignatureAlgorithm.HS512, "PrivateSecret").compact();
//            RootResult<String> result = new RootResult<>();
//            result.setData(SessionContent.USER_TOKEN_INVALID);
//            result.setMsg(JwtUtils.getTokenHeader(token));
//            response.reset();
//            response.setContentType("text/json;charset=UTF-8");
//            response.getWriter()
//                    .write(JSON.toJSONString(result));
//            return;
//        }

        //校验session的user
        boolean needValidUser = true;
        for (String allowUri : ALLOWED_URIS) {
            if (requestURI.contains(allowUri)) {
                needValidUser = false;
                break;
            }
        }
        if (!needValidUser) {
            chain.doFilter(req, res);
            return;
        }

        //判断session是否有用户，没有就返回标识
        SysStaffInputVO sysUserInputVO = SessionContent.getLoginUser(request);
        if (sysUserInputVO == null) {
            log.error("用户未登录或已失效，请求失败！ request：{}", request);
            RootResult<String> result = new RootResult<>();
            result.setData(SessionContent.USER_SESSION_INVALID);
            response.reset();
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter()
                .write(JSON.toJSONString(result));
            return;
        } else{
            RequestHolder.add(sysUserInputVO);
        }


        chain.doFilter(req, res);
    }

    /**
     * 根据配置文件，生成允许通过的路径集合
     *
     * @param filterConfig
     * @date: 2018/11/14 11:24
     * @author:
     */
    @Override
    public void init(FilterConfig filterConfig) {
        String allowedUris = filterConfig.getInitParameter("allowedUris");
        String[] uriArray = StringUtils.split(allowedUris, ",");

        HashSet<String> uriSet = new HashSet<>();

        if (ArrayUtils.isNotEmpty(uriArray)) {
            Arrays.stream(uriArray)
                    .forEach(e -> uriSet.add(StringUtils.trim(e)));
        }

        ALLOWED_URIS = Collections.unmodifiableSet(uriSet);
    }

    @Override
    public void destroy() {
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        String user = Jwts.parser()
                .setSigningKey("PrivateSecret")
                .parseClaimsJws(token.replace(JwtUtils.getAuthorizationHeaderPrefix(), ""))
                .getBody()
                .getSubject();
        if (null != user) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
    }

}