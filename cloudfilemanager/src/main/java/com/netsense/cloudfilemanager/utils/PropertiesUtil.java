package com.netsense.cloudfilemanager.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 根据属性文件和 xxx.properties和属性名获取属性值
 * 
 * @param fileName
 *            xxx.properties
 * @param propertieName
 *            属性名
 * @return
 */
public class PropertiesUtil {
	private static final Log logger = LogFactory.getLog(PropertiesUtil.class);
	private static Map<String, Properties> map = new HashMap<String, Properties>();

	private PropertiesUtil() {
	}

	/**
	 * 根据文件名获取所有属性
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		Properties properties = map.get(fileName);
		if (properties != null)
			return properties;
		// 缓存中不存在，重新加载
		initProperties(fileName);
		return map.get(fileName);
	}

	/**
	 * 根据文件名和属性名获取属性值
	 * 
	 * @param fileName
	 * @param propertieName
	 * @return
	 */
	public static String getPropertieValue(String fileName, String propertieName) {
		Properties properties = map.get(fileName);
		if (properties != null)
			return properties.getProperty(propertieName);
		// 缓存中不存在，重新加载
		initProperties(fileName);
		properties = map.get(fileName);
		if (properties != null)
			return properties.getProperty(propertieName);
		return null;
	}

	/**
	 * 对某个属性文件进行初始化
	 * 
	 * @param fileName
	 */
	private static void initProperties(String fileName) {
		try {
			Properties properties = new Properties();
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(inputStream);
			map.put(fileName, properties);
			inputStream.close();
		} catch (IOException e) {
			logger.error("com.sxit.ecwx.core.utils.PropertiesUtil.initProperties", e);
		}
	}
}