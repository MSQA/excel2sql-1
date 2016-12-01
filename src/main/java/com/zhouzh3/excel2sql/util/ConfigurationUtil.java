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
}
