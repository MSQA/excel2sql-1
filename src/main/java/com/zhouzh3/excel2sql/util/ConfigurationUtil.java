package com.zhouzh3.excel2sql.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.zhouzh3.excel2sql.model.Context;
import com.zhouzh3.excel2sql.model.JdbcContext;

public class ConfigurationUtil {

	private ConfigurationUtil() {
	}

	public static Configuration buildConfiguration(String... fileNames) {
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class).configure(getParameters(fileNames));
		try {
			return builder.getConfiguration();
		} catch (ConfigurationException cex) {
			throw new RuntimeException("加载配置文件失败:" + cex.getMessage(), cex);
		}
	}

	protected static PropertiesBuilderParameters[] getParameters(String... fileNames) {
		List<PropertiesBuilderParameters> answer = new ArrayList<PropertiesBuilderParameters>();
		for (String fileName : fileNames) {
			answer.add(getParameters(fileName));
		}
		return answer.toArray(new PropertiesBuilderParameters[answer.size()]);
	}

	protected static PropertiesBuilderParameters getParameters(String fileName) {
		PropertiesBuilderParameters properties = new Parameters().properties();
		properties.setEncoding("UTF-8");
		properties.setURL(FileUtil.getResource(fileName));
		return properties;
	}

	public static Context getContext(Configuration configuration) {
		Context context = new Context();
		// 需要刷新的表
		context.setExcelFile(FileUtil.getFile(configuration.getString("excel.file")));
		String line = configuration.getString("sheet.names");
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

		JdbcContext jdbcContext = new JdbcContext(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword);
		context.setJdbcContext(jdbcContext);

		return context;
	}

	public static Context getContext(String... fileNames) {
		return getContext(buildConfiguration(fileNames));
	}
}
