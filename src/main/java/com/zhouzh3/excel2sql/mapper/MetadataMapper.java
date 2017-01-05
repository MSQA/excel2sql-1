package com.zhouzh3.excel2sql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface MetadataMapper {
	@Select("SELECT SCHEMA_NAME FROM information_schema.SCHEMATA t"//
			+ " WHERE t.SCHEMA_NAME NOT IN ( 'information_schema', 'mysql', 'performance_schema', 'sys' )"//
			+ " ORDER BY t.SCHEMA_NAME"//
			+ " limit 1")
	List<String> querySchemas();
	
	

}