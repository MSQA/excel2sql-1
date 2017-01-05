package com.zhouzh3.excel2sql.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.zhouzh3.excel2sql.model.DataSource;

/**
 *
 * @author Dhinakaran Pragasam
 */
public class ScriptRunnerUtil {

	private ScriptRunnerUtil() {
	}

	public static void runScript(DataSource jdbcContext, Reader in) {
		ScriptRunner sr = null;
		try {
			Connection connection = jdbcContext.getConnection();
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
		runScript(null, new StringReader("DROP TABLE IF EXISTS T_URM_ORG_TREE_HIS;"));
	}
}