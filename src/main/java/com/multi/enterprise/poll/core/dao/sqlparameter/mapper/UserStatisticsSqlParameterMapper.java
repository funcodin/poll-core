/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.accounts.UserStatistics;

/**
 * @author Robot
 *
 */
@Repository
public class UserStatisticsSqlParameterMapper extends BaseSqlParameterMapper<UserStatistics> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "user_statistics";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return "user_id";
	}

	public OrderedMapSqlParameterSource mapInsertSqlParams(final UserStatistics userStats) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("id", userStats.getId());
		orderedMapSqlParameterSource.addValue("user_id", userStats.getUserId());
		orderedMapSqlParameterSource.addValue("questions_answered", userStats.getQuestionsAnswered());
		orderedMapSqlParameterSource.addValue("questions_asked", userStats.getQuestionsAsked());
		orderedMapSqlParameterSource.addValue("user_comments", userStats.getCommentCount());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;

	}
}
