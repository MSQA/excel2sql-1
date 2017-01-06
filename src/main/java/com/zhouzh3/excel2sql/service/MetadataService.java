package com.zhouzh3.excel2sql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.zhouzh3.excel2sql.mapper.MySqlMapper;
import com.zhouzh3.excel2sql.model.Column;
import com.zhouzh3.excel2sql.model.Schema;
import com.zhouzh3.excel2sql.model.Table;

public class MetadataService {

	protected static Logger logger = Logger.getLogger(MetadataService.class);

	private SqlSessionFactory sqlSessionFactory;

	public MetadataService(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public <R> R query(Map<String, String> params, BiFunction<MySqlMapper, Map<String, String>, R> fun) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MySqlMapper mapper = session.getMapper(MySqlMapper.class);
			return fun.apply(mapper, params);
		} finally {
			session.close();
		}
	}

	public List<Schema> querySchemas() {
		return query(null, (MySqlMapper t, Map<String, String> u) -> {
			return t.querySchemas();
		});
	}

	public List<Schema> querySchemas(String tableSchema) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("schemaName", tableSchema);
		return query(params, (MySqlMapper t, Map<String, String> u) -> {
			return t.queryOneSchema(params);
		});
	}

	public List<Table> queryTables(String tableSchema) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tableSchema", tableSchema);
		return query(params, (MySqlMapper t, Map<String, String> u) -> {
			return t.queryTables(u);
		});
	}

	public List<Column> queryColumns(String tableSchema, String tableName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("tableSchema", tableSchema);
		params.put("tableName", tableName);
		return query(params, (MySqlMapper t, Map<String, String> u) -> {
			return t.queryColumns(u);
		});
	};

}
