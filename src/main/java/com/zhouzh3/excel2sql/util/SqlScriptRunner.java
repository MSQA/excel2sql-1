package com.zhouzh3.excel2sql.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author Dhinakaran Pragasam
 */
public class SqlScriptRunner {

	public SqlScriptRunner() {
	}

	public void runScript(String scriptFilePath) {
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(scriptFilePath), "UTF-8");
			runScript(in);
		} catch (UnsupportedEncodingException | FileNotFoundException | RuntimeException e) {
			System.err.println("Failed to Execute" + scriptFilePath + " The error is " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	public void runScript(Reader in) {
		ScriptRunner sr = null;
		try {
			Connection connection = ConnectFactory.getConnection();
			sr = new ScriptRunner(connection);
			sr.runScript(new BufferedReader(in));
			sr.setLogWriter(new PrintWriter(System.out));
			sr.setErrorLogWriter(new PrintWriter(System.err));

		} catch (Exception e) {
			System.err.println("Failed to Execute" + " The error is " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			if (sr != null) {
				sr.closeConnection();
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SqlScriptRunner runner = new SqlScriptRunner();

		// String scriptFilePath = "src/main/resources/script.sql";
		// sqlScriptRunner.runScript(scriptFilePath);

		runner.runScript(new StringReader("DROP TABLE IF EXISTS T_URM_ORG_TREE_HIS;"));

	}
}