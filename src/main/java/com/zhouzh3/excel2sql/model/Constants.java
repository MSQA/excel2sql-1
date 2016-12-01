package com.zhouzh3.excel2sql.model;

public interface Constants {

	public static String[] COLUMN_NAMES = { "TABLE", "NAME", "CODE", "DATATYPE", "NOTNULL", "COMMENT" };

	public static int FIRST_ROW = 1;

	public static String COMMENT_SHEET_PREFIX = "@#$";

	public static String NEW_LINE = System.getProperty("line.separator", "\n");

	public static String COMMA = ",";

	public String FILE_JDBC = "conf/jdbc.properties";

	public String FILE_EXCEL2SQL = "conf/excel2sql.properties";

}
