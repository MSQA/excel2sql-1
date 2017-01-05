package com.zhouzh3.excel2sql.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;

public class FileUtil {

	protected static Logger logger = Logger.getLogger(FileUtil.class);

	public static void save2file(String fileName, String charsetName, String content) throws IOException {
		// File file = getFile(fileName);
		File file = new File(fileName);
		logger.debug("保存内容到文件：" + file.getAbsolutePath());
		FileOutputStream fileWriter = new FileOutputStream(file);
		OutputStreamWriter out = new OutputStreamWriter(fileWriter, charsetName);
		BufferedWriter bw = new BufferedWriter(out);
		bw.write(content);
		bw.flush();
		bw.close();
	}

	public static void save2file(String fileName, String content) throws IOException {
		save2file(fileName, "UTF-8", content);
	}

	public static URL getResource(String fileName) {
		return FileUtil.class.getClassLoader().getResource(fileName);
	}

	public static File getFile(String fileName) {
		File file = new File(fileName);
		if (file.isAbsolute()) {
			return file;
		}
		try {
			URL resource = getResource(fileName);
			if (resource != null) {
				return new File(resource.toURI());
			}
		} catch (URISyntaxException e) {
			throw new RuntimeException("打开文件" + fileName + "失败:" + e.getMessage(), e);
		}
		throw new RuntimeException("打开文件" + fileName + "失败");
	}

}
