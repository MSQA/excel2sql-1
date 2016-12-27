package com.zhouzh3.excel2sql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zhouzh3.excel2sql.model.JdbcContext;

public class ConnectFactory {

	public static Connection getConnection(JdbcContext jdbcContext) throws SQLException {
		try {
			Class.forName(jdbcContext.getJdbcDriver());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}

		return DriverManager.getConnection(jdbcContext.getJdbcUrl(), jdbcContext.getJdbcUser(),
				jdbcContext.getJdbcPassword());
	}

//	private static Connection getConnection() throws SQLException {
//		Configuration configuration = ConfigurationUtil.buildConfiguration(Constants.FILE_JDBC);
//		String driver = configuration.getString("jdbc.driver");
//		String url = configuration.getString("jdbc.url");
//		String user = configuration.getString("jdbc.username");
//		String password = configuration.getString("jdbc.password");
//
//		JdbcContext parameterObject = new JdbcContext(driver, url, user, password);
//		return getConnection(parameterObject);
//	}

}
