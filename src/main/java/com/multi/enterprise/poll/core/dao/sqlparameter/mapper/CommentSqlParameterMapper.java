/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.Comment;

/**
 * @author Robot
 *
 */

@Repository
public class CommentSqlParameterMapper extends BaseSqlParameterMapper<Comment> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "user_comments";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return "question_id";
	}

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final Comment comment) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("user_id", comment.getUserId());
		orderedMapSqlParameterSource.addValue("question_id", comment.getQuestionId());
		orderedMapSqlParameterSource.addValue("comment", comment.getComment());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());
		return orderedMapSqlParameterSource;
	}
}
