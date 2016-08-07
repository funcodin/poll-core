/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Repository
public class QuestionSqlParameterMapper extends BaseSqlParameterMapper<Question> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	public String getTableName() {
		return "question";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	public String getIdColumnName() {
		return "id";
	}

}
