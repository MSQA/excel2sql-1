package com.zhouzh3.excel2sql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.zhouzh3.excel2sql.model.Column;
import com.zhouzh3.excel2sql.model.Schema;
import com.zhouzh3.excel2sql.model.Table;

public interface MySqlMapper extends MetadataMapper {
	@Select("SELECT SCHEMA_NAME CODE FROM information_schema.SCHEMATA t"//
			+ " WHERE t.SCHEMA_NAME NOT IN ( 'information_schema', 'mysql', 'performance_schema', 'sys' )"//
			// + " AND t.SCHEMA_NAME = 'appcenter'"
			+ " ORDER BY t.SCHEMA_NAME"//
	// + " limit 1"
	)
	List<Schema> querySchemas();
	
	@Select("SELECT SCHEMA_NAME CODE FROM information_schema.SCHEMATA t"//
			+ " WHERE t.SCHEMA_NAME NOT IN ( 'information_schema', 'mysql', 'performance_schema', 'sys' )"//
			+ " AND t.SCHEMA_NAME = #{schemaName}"
			+ " ORDER BY t.SCHEMA_NAME"//
			// + " limit 1"
			)
	List<Schema> queryOneSchema(Map<String, String> params);

	@Select("SELECT t.TABLE_NAME CODE, IF (t.TABLE_COMMENT <> '', t.TABLE_COMMENT, t.TABLE_NAME) NAME FROM information_schema. TABLES t WHERE t.TABLE_TYPE = 'BASE TABLE' AND t.TABLE_SCHEMA = #{tableSchema}")
	List<Table> queryTables(Map<String, String> params);

	@Select("SELECT t.COLUMN_NAME NAME, t.COLUMN_NAME CODE, t.COLUMN_TYPE datatype, IF (t.IS_NULLABLE = 'NO', TRUE, FALSE) not_null, t.COLUMN_COMMENT COMMENT"//
			+ " FROM information_schema. COLUMNS t"//
			+ " WHERE t.TABLE_SCHEMA = #{tableSchema} and t.TABLE_NAME = #{tableName}"//
			+ " ORDER BY t.TABLE_NAME, t.ORDINAL_POSITION")
	List<Column> queryColumns(Map<String, String> params);

}