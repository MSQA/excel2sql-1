package com.zhouzh3.excel2sql;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.zhouzh3.excel2sql.model.Constants;
import com.zhouzh3.excel2sql.model.Table;
import com.zhouzh3.excel2sql.parse.ExcelParser;
import com.zhouzh3.excel2sql.util.ConfigurationUtil;
import com.zhouzh3.excel2sql.util.FileUtil;

public class SqlGenRun {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		Configuration configuration = ConfigurationUtil.buildConfiguration(Constants.FILE_EXCEL2SQL);

		execute(configuration);

	}

	private static void execute(Configuration configuration) throws IOException, InvalidFormatException {
		String fileName = configuration.getString("excel.filename");
		File file = FileUtil.getFile(fileName);

		String[] sheetNames = configuration.getStringArray("sheet.names");

		ExcelParser excel = new ExcelParser();
		List<Table> tables = excel.xlsx2table(file, sheetNames);

		String script = excel.combineSql(tables);

		boolean saveFile = configuration.getBoolean("save.script", true);
		String scriptFileName = configuration.getString("script.filename");
		if (saveFile) {
			excel.saveScript(scriptFileName, script, saveFile);
		}

		boolean isExecute = configuration.getBoolean("execute.script", true);
		if (isExecute) {
			excel.executeSql(script, isExecute);
		}

		// System.out.println(script);
		for (Table table : new TreeSet<>(tables)) {
			System.out.println(table.getXmlElement());
		}
	}
}
