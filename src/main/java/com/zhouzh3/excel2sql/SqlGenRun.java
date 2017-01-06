package com.zhouzh3.excel2sql;

import java.io.IOException;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.zhouzh3.excel2sql.model.Context;
import com.zhouzh3.excel2sql.parse.ContextParser;
import com.zhouzh3.excel2sql.parse.ExcelParser;

public class SqlGenRun {

	public static void main(String[] args) throws IOException, InvalidFormatException, ConfigurationException {
		// String[] files = new String[] { "conf/sample-jdbc.properties",
		// "conf/sample-app.properties", };

		// String[] files = new String[] { "conf/urm-jdbc.properties",
		// "conf/urm-app.properties", };

		String[] files = new String[] { "conf/apc-jdbc.properties", "conf/apc-app.properties", };

		Context context = ContextParser.parseContext(files);
		new ExcelParser().execute(context);
	}
}
