package com.zhouzh3.excel2sql;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.zhouzh3.excel2sql.model.Context;
import com.zhouzh3.excel2sql.parse.ExcelParser;
import com.zhouzh3.excel2sql.util.ConfigurationUtil;

public class SqlGenRun {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		Context context = ConfigurationUtil.getContext("conf/excel2sql.properties", "conf/jdbc.properties");

		new ExcelParser().execute(context);
	}
}
