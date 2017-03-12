/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */

@Repository
public class UserPollSqlParameterMapper extends BaseSqlParameterMapper<UserPoll> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "user_poll";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return "option_id";
	}

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final UserPoll userPoll) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("user_id", userPoll.getUserId());
		orderedMapSqlParameterSource.addValue("option_id", userPoll.getOptionId());
		orderedMapSqlParameterSource.addValue("question_id", userPoll.getQuestionId());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;

	}
}
