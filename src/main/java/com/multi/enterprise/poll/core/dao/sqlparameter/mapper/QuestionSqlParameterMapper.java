/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
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

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final Question question) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("id", question.getId());
		orderedMapSqlParameterSource.addValue("userId", question.getUserId());
		orderedMapSqlParameterSource.addValue("question", question.getQuestion());
		orderedMapSqlParameterSource.addValue("option_type", question.getOptionType());
		orderedMapSqlParameterSource.addValue("qr_code_url", question.getQrCodeUrl());
		orderedMapSqlParameterSource.addValue("media_url", question.getMediaUrl());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;
	}

}
