package com.zhouzh3.excel2sql.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.zhouzh3.excel2sql.model.Column;
import com.zhouzh3.excel2sql.model.Schema;
import com.zhouzh3.excel2sql.model.Table;
import com.zhouzh3.excel2sql.service.MetadataService;
import com.zhouzh3.excel2sql.util.SqlSessionFactoryUtil;

public class MySqlServiceTest {

	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MySqlServiceTest.class);

	public void querySchemas() {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		MetadataService mySqlService = new MetadataService(sqlSessionFactory);
		List<Schema> schemas = mySqlService.querySchemas();
		for (Schema schema : schemas) {
			logger.debug(schema);
		}

	}

	public void queryColumns() {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		MetadataService mySqlService = new MetadataService(sqlSessionFactory);
		List<Column> columns = mySqlService.queryColumns("appcenter", "t_apc_app");

		for (Column column : columns) {
			logger.debug(column);
		}

	}

	@Test
	public void queryTables() {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
		MetadataService mySqlService = new MetadataService(sqlSessionFactory);
		List<Table> tables = mySqlService.queryTables("appcenter");

		for (Table table : tables) {
			logger.debug(table);
		}

	}

}
