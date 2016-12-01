package com.zhouzh3.excel2sql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration2.Configuration;

import com.zhouzh3.excel2sql.model.Constants;

public class ConnectFactory {

	public static Connection getConnection(String driver, String url, String user, String password)
			throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}

		return DriverManager.getConnection(url, user, password);
	}

	public static Connection getConnection() throws SQLException {
		Configuration configuration = ConfigurationUtil.buildConfiguration(Constants.FILE_JDBC);
		String driver = configuration.getString("jdbc.driver");
		String url = configuration.getString("jdbc.url");
		String user = configuration.getString("jdbc.username");
		String password = configuration.getString("jdbc.password");

		return getConnection(driver, url, user, password);
	}

}
