package com.zhouzh3.excel2sql.mapper;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

public class MySqlService {

	protected static Logger logger = Logger.getLogger(MySqlService.class);

	private SqlSessionFactory sqlSessionFactory;

	public MySqlService() {
		querySchemas();
	}

	private List<String> querySchemas() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MetadataMapper mapper = session.getMapper(MetadataMapper.class);
			return mapper.querySchemas();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) throws IOException {

	}
}
