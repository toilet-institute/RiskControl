package com.jxlt.udic.riskcontrol.website.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author 作者 NCPLT:
 * @version 创建时间：
 */
public class CommonUtil {
	/**
	 * 获取原始密码
	 * @param originPassword
	 * @return
	 */
	public static String getLoginPasswod(String originPassword) {
		try {
			String password1 = new String(Base64.decodeBase64(originPassword), "UTF-8");
			String password2 = new String(Base64.decodeBase64(password1.substring(2)), "UTF-8");
			String password3 = new BigInteger(EncryptUtil.encryptSHA(password2.getBytes())).toString().toLowerCase();
			return password3;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}
}
