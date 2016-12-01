package com.zhouzh3.excel2sql.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

	public static final String NEW_LINE = System.getProperty("line.separator", "\n");

	private static Properties properties = new Properties();

	static {
		load("jdbc.properties");
		load("excel2sql.properties");
	}

	private static void load(String name) {
		try {
			InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
			properties.load(new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8")));
		} catch (IOException e) {
			throw new RuntimeException("读取配置文件" + name + "失败", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String[] getArray(String key) {
		String property = properties.getProperty(key);
		if (property == null) {
			return new String[0];
		}
		return property.split(",\\s*");
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static final String COMMA = ",";

	public static boolean getBoolean(String key, String defaultValue) {
		return Boolean.parseBoolean(getProperty(key, defaultValue));
	}

	public static File getExcelFile() {
		return new File(getProperty("excel.dir"));
	}

}
