package com.jxlt.udic.riskcontrol.website.util;

import com.jxlt.udic.riskcontrol.website.model.input.SysStaffInputVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于存储用户信息
 *
 * @author NCPLT-2020
 * @date
 */
public class RequestHolder {

    private static boolean useTempSessionUser;
    private static final ThreadLocal<SysStaffInputVO> USER_HOLDER = new ThreadLocal<SysStaffInputVO>();
    private static final ThreadLocal<HttpServletRequest> REQUEST_HOLDER = new ThreadLocal<HttpServletRequest>();
    public static void add(SysStaffInputVO sysUserInputVO) {
        USER_HOLDER.set(sysUserInputVO);
    }
    public static void add(HttpServletRequest request) {
        REQUEST_HOLDER.set(request);
    }

    public static SysStaffInputVO getCurrentUser() {

        //前端调试
        if (USER_HOLDER.get() == null) {
            if (useTempSessionUser) {
                SysStaffInputVO sysUser = new SysStaffInputVO();
                sysUser.setId("3e3644bcdf7046b5b764dd8b576ec1fb");
                sysUser.setOrgcode("6d1b050e8e1a483fa7c706c29e55588d");
                RequestHolder.add(sysUser);
            }
        }

        return USER_HOLDER.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return REQUEST_HOLDER.get();
    }

    public static void remove() {
        USER_HOLDER.remove();
        REQUEST_HOLDER.remove();
    }

    public static void setUseTempSessionUser(boolean useTempSessionUser) {
        RequestHolder.useTempSessionUser = useTempSessionUser;
    }
}