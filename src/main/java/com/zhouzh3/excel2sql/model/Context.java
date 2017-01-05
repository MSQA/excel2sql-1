package com.zhouzh3.excel2sql.model;

import java.io.File;
import java.util.Arrays;

public class Context {
	private File excelFile;
	private String[] sheetNames;
	private boolean scriptSave;
	private String scriptFile;
	private boolean executeSql;

	private DataSource dataSource;

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String[] getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}

	public boolean isScriptSave() {
		return scriptSave;
	}

	public void setScriptSave(boolean scriptSave) {
		this.scriptSave = scriptSave;
	}

	public String getScriptFile() {
		return scriptFile;
	}

	public void setScriptFile(String scriptFile) {
		this.scriptFile = scriptFile;
	}

	public boolean isExecuteSql() {
		return executeSql;
	}

	public void setExecuteSql(boolean executeSql) {
		this.executeSql = executeSql;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource jdbcContext) {
		this.dataSource = jdbcContext;
	}

	@Override
	public String toString() {
		return "Context [excelFile=" + excelFile + ", sheetNames=" + Arrays.toString(sheetNames) + ", saveFile="
				+ scriptSave + ", scriptFileName=" + scriptFile + ", isExecute=" + executeSql + "]";
	}

}
