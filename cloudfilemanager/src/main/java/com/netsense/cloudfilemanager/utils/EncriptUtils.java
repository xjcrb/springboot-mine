package com.netsense.cloudfilemanager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


/**
 * MD5加密工具
 *
 */
public final class EncriptUtils {
	/** 日志对象 */
	private static final Logger logger = LoggerFactory.getLogger(EncriptUtils.class);
	/**
	 * 加密用的密钥
	 */
//	private static String EncriptKey = Constant.ENCRIPT_KEY;

	/**
	 * 十六进制下数字到字符的映射数组
	 */
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f"};

//	public static String getEncriptKey() {
	//	return EncriptKey;
//	}

	//public static void setEncriptKey(String encriptKey) {
		//EncriptKey = encriptKey;
	//}

	/**
	 * 把inputString加密
	 *
	 * @param inputStr
	 * @return
	 */
	public static String md5(String inputStr) {
		return encodeByMD5(inputStr);
	}

	/**
	 * 把inputString加密返回16个字符
	 *
	 * @param inputStr
	 * @return
	 */
	public static String md5_16(String inputStr) {
		String md5_32 = encodeByMD5(inputStr);
		return md5_32.substring(8, 24);
	}

	/**
	 * 验证输入的密码是否正确
	 *
	 * @param password 真正的密码（加密后的真密码）
	 * @param inputString 输入的字符串
	 * @return 验证结果，boolean类型
	 */
	public static boolean authenticatePassword(String password, String inputString) {
		if (password.equals(encodeByMD5(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对字符串进行MD5编码
	 *
	 * @param originString
	 * @return
	 */
	public static String encodeByMD5(String originString) {
		if (originString == null) {
			return null;
		}
		try {
			// 创建具有指定算法名称的信息摘要
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
			byte[] results = md5.digest(originString.getBytes());
			// 将得到的字节数组变成字符串返回
			String result = byteArrayToHexString(results);
			String result1=	result.toUpperCase();
			return result1;
		} catch (Exception e) {
			logger.error("com.netsense.common.utils.EncriptUtils.encodeByMD5 exception!", e);
		}
		return null;
	}


	/**
	 * 轮换字节数组为十六进制字符串
	 *
	 * @param b 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转化成十六进制形式的字符串
	 *
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String[] args) {
		System.out.println(encodeByMD5("123456"));
		System.out.println(md5("123456"));
	}

}
