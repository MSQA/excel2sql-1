package com.zhouzh3.excel2sql.util;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigurationUtil {

	private ConfigurationUtil() {
	}

	public static Configuration buildCompositeConfiguration(String... fileNames) throws ConfigurationException {
		CompositeConfiguration composite = new CompositeConfiguration();
		for (String fileName : fileNames) {
			composite.addConfiguration(buildConfiguration(fileName));
		}
		return composite;
	}

	private static Configuration buildConfiguration(String fileName) {
		FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<PropertiesConfiguration>(
				PropertiesConfiguration.class);
		builder.configure(getParameters(fileName));
		try {
			return builder.getConfiguration();
		} catch (ConfigurationException cex) {
			throw new RuntimeException("加载配置文件失败:" + cex.getMessage(), cex);
		}
	}

	protected static PropertiesBuilderParameters getParameters(String fileName) {
		PropertiesBuilderParameters properties = new Parameters().properties();
		properties.setEncoding("UTF-8");
		properties.setURL(FileUtil.getResource(fileName));
		return properties;
	}
}
