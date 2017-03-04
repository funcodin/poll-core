/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.friends.UserFriendAction;

/**
 * @author Robot
 *
 */

@Repository
public class UserFriendActionSqlParameterMapper extends BaseSqlParameterMapper<UserFriendAction> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "user_friends";
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

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final UserFriendAction userFriend) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("user_id", userFriend.getAccepterUserId());
		orderedMapSqlParameterSource.addValue("friends_id", userFriend.getRequesterUserId());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());
		return orderedMapSqlParameterSource;

	}

}
