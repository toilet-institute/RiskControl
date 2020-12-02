package com.jxlt.udic.riskcontrol.website.util;

import javax.servlet.http.HttpServletRequest;

//import com.wxapp.ncplt.model.SysMenu;
import com.jxlt.udic.riskcontrol.website.model.input.SysStaffInputVO;

/**
 * Sessiong保存内容获取工具。
 *
 */
public class SessionContent {

	private static boolean useTempSessionUser;
	
	private static final String USER_MENU_LIST_KEY="userMenuList";

	/**
	 * 用户session无效标志
	 */
	public static final String USER_SESSION_INVALID = "user_session_invalid";
	/**
	 * 用户token无效标志
	 */
	public static final String USER_TOKEN_INVALID = "user_token_invalid";
	
	/**
	 * 保存验证码
	 * @param req
	 * @param validateCode
	 */
	public static void setValidateCode(HttpServletRequest req, String validateCode){
		req.getSession().setAttribute("validateCode", validateCode);
	}
	
	/**
	 * 获取验证码
	 * @param req
	 * @return
	 */
	public static String getValidateCode(HttpServletRequest req){
		String validateCode = "";
		Object object = req.getSession().getAttribute("validateCode");
		if(object != null){
			validateCode = (String)object;
		}
		
		return validateCode;
	}
	
	/**
	 * 移除验证码
	 * @param req
	 */
	public static void removeValidateCode(HttpServletRequest req){
		req.getSession().removeAttribute("validateCode");
	}
	
	/**
	 * 保存登录用户信息
	 * @param req
	 * @param userMessager
	 */
	public static void setLoginUser(HttpServletRequest req, SysStaffInputVO userMessager){
		req.getSession().setAttribute("loginUser", userMessager);
	}
	
	/**
	 * 获取登录用户信息
	 * @param req
	 * @return
	 */
	public static SysStaffInputVO getLoginUser(HttpServletRequest req){
		SysStaffInputVO sysPerson = null;
		Object object = req.getSession().getAttribute("loginUser");
		if(object != null){
			sysPerson = (SysStaffInputVO)object;
		}

		//前端调试
		if (sysPerson == null) {
			if (useTempSessionUser) {
				sysPerson = new SysStaffInputVO();
				sysPerson.setId("3e3644bcdf7046b5b764dd8b576ec1fb");
                sysPerson.setOrgcode("5a7be83c20ee44009448994ba315e530");
				RequestHolder.add(sysPerson);
				SessionContent.setLoginUser(req, sysPerson);
			}
		}
		
		return sysPerson;
	}
	
	/**
	 * 移除登录用户信息
	 * @param req
	 */
	public static void removeLoginUser(HttpServletRequest req){
		req.getSession().removeAttribute("loginUser");
	}

	public static void setUseTempSessionUser(boolean useTempSessionUser) {
		SessionContent.useTempSessionUser = useTempSessionUser;
	}
//	/**
//	 * 设置用户列表
//	 */
//	public static void setUserMenu(HttpServletRequest req,List<SysMenu> menuList) {
//		req.getSession().setAttribute(USER_MENU_LIST_KEY, menuList);
//	}
//	/**
//	 * 移除用户菜单列表
//	 */
//	public static void removeUserMenu(HttpServletRequest req) {
//		req.getSession().removeAttribute(USER_MENU_LIST_KEY);
//	}
//	/**
//	 * 获取用户菜单
//	 */
//	public static List<SysMenu> getUserMenuList(HttpServletRequest req) {
//		Object object = req.getSession().getAttribute(USER_MENU_LIST_KEY);
//		if(object == null) {
//			return new ArrayList<>();
//		}
//		return (List<SysMenu>)object;
//	}
}