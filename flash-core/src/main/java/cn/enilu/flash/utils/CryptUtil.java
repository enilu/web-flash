package cn.enilu.flash.utils;


import java.util.Base64;

/**
 * 加密工具类
 * @author  enilu
 */
public class CryptUtil {

	public static String encodeBASE64(byte[] bytes) {

		String encode = Base64.getEncoder().encodeToString(bytes);
		encode = encode.replaceAll("\n", "");
		return encode;
	}


}
