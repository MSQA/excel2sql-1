package com.zhouzh3.excel2sql;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.zhouzh3.excel2sql.model.Column;
import com.zhouzh3.excel2sql.model.Schema;
import com.zhouzh3.excel2sql.model.Table;
import com.zhouzh3.excel2sql.service.MetadataService;
import com.zhouzh3.excel2sql.util.SqlSessionFactoryUtil;

public class XlsxGenRun {
	protected static Logger logger = Logger.getLogger(XlsxGenRun.class);

	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		MetadataService service = new MetadataService(sqlSessionFactory);

		List<Schema> database = service.querySchemas();
		for (Schema schema : database) {
			List<Table> tables = service.queryTables(schema.getCode());
			schema.setTables(tables);
			for (Table table : schema.getTables()) {
				logger.debug(table);
				List<Column> columns = service.queryColumns(schema.getCode(), table.getCode());
				table.addColumns(columns);
				logger.debug("============================================");
				logger.debug(schema.getCode() + ": " + table.getCode());
				for (Column column : columns) {
					logger.debug(column);
				}
				logger.debug("============================================");
			}
			// 保存到文件中
			try {
				String format = MessageFormat.format("database/{0}.xlsx", schema.getCode());
				schema.writeExcel(format);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
