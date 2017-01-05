package com.zhouzh3.excel2sql.parse;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.zhouzh3.excel2sql.model.Context;
import com.zhouzh3.excel2sql.model.DataSource;
import com.zhouzh3.excel2sql.util.ConfigurationUtil;
import com.zhouzh3.excel2sql.util.FileUtil;
import com.zhouzh3.excel2sql.util.StringUtil;

public class ContextParser {

	private static Context parseContext(Configuration configuration) {
		Context context = new Context();
		// 需要刷新的表
		String string = configuration.getString("excel.file");
		context.setExcelFile(FileUtil.getFile(string));
		String line = configuration.getString("excel.sheet");
		if (!StringUtil.isBlank(line)) {
			context.setSheetNames(line.trim().split("[, ]+"));
		} else {
			context.setSheetNames(new String[0]);
		}
	
		// 是否保存脚本
		context.setScriptSave(configuration.getBoolean("script.save", true));
		context.setScriptFile(configuration.getString("script.file"));
	
		// 是否执行脚本
		context.setExecuteSql(configuration.getBoolean("execute.sql", true));
	
		String jdbcDriver = configuration.getString("jdbc.driver");
		String jdbcUrl = configuration.getString("jdbc.url");
		String jdbcUser = configuration.getString("jdbc.username");
		String jdbcPassword = configuration.getString("jdbc.password");
	
		DataSource jdbcContext = new DataSource(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
		context.setDataSource(jdbcContext);
	
		return context;
	}

	public static Context parseContext(String... fileNames) throws ConfigurationException {
		Configuration configuration = ConfigurationUtil.buildCompositeConfiguration(fileNames);
	
		return parseContext(configuration);
	}

}
