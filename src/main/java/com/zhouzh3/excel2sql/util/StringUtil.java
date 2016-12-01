package com.zhouzh3.excel2sql.util;

public class StringUtil {

	public static boolean isNotBlank(String token) {
		return token != null && token.trim().length() > 0;
	}

	public static boolean isBlank(String token) {
		return token == null || token.trim().length() == 0;
	}

	public static String coalesce(String... tokens) {
		for (String token : tokens) {
			if (isNotBlank(token)) {
				return token;
			}
		}

		return null;
	}

}