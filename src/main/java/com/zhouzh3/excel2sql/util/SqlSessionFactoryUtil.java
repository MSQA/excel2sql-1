package com.zhouzh3.excel2sql.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory = null;

	private SqlSessionFactoryUtil() {
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			init();
		}
		return sqlSessionFactory;
	}

	private static synchronized void init() {
		if (sqlSessionFactory != null) {
			return;
		}
		String resource = "conf/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
