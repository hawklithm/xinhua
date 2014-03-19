package com.multiagent.hawklithm.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
/**
 * ∂¡–¥∑÷¿Î
 * @author hawklithm
 *
 */
public class IbatisManagerModule {
	@SuppressWarnings("deprecation")
	private SqlMapClientTemplate readerSqlMapClientTemplate;
	@SuppressWarnings("deprecation")
	private SqlMapClientTemplate writerSqlMapClientTemplate;

	@SuppressWarnings("deprecation")
	public Object insert(String statementName, Map<?, ?> paramMap) throws DataAccessException {
		return writerSqlMapClientTemplate.insert(statementName, paramMap);
	}

	@SuppressWarnings("deprecation")
	public List<?> select(String statementName, Map<?, ?> paramMap) throws DataAccessException {
		return readerSqlMapClientTemplate.queryForList(statementName, paramMap);
	}
	@SuppressWarnings("deprecation")
	public int update(String statementName, Map<?, ?> paramMap) throws DataAccessException{
		return writerSqlMapClientTemplate.update(statementName,paramMap);
	}
	@SuppressWarnings("deprecation")
	public int delete(String statementName, Map<?, ?> paramMap) throws DataAccessException{
		return writerSqlMapClientTemplate.delete(statementName, paramMap);
	}

	public SqlMapClientTemplate getReaderSqlMapClientTemplate() {
		return readerSqlMapClientTemplate;
	}

	public void setReaderSqlMapClientTemplate(SqlMapClientTemplate readerSqlMapClientTemplate) {
		this.readerSqlMapClientTemplate = readerSqlMapClientTemplate;
	}

	public SqlMapClientTemplate getWriterSqlMapClientTemplate() {
		return writerSqlMapClientTemplate;
	}

	public void setWriterSqlMapClientTemplate(SqlMapClientTemplate writerSqlMapClientTemplate) {
		this.writerSqlMapClientTemplate = writerSqlMapClientTemplate;
	}

}
