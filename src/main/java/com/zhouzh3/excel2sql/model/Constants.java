package com.zhouzh3.excel2sql.model;

public interface Constants {

	public static String[] COLUMN_NAMES = { "Table", "Name", "Code", "Datatype", "NotNull", "Comment", };

	public static int FIRST_ROW = 1;

	public static String COMMENT_SHEET_PREFIX = "@#$";

	public static String NEW_LINE = System.getProperty("line.separator", "\n");

	public static String COMMA = ",";

}
