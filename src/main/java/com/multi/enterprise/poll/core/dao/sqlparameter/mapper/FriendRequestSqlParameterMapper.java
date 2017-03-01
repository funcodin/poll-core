/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.friends.FriendRequest;

/**
 * @author Robot
 *
 */
@Repository
public class FriendRequestSqlParameterMapper extends BaseSqlParameterMapper<FriendRequest> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "pending_friend_request";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return "requester_user_id";
	}

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final FriendRequest options) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();

		orderedMapSqlParameterSource.addValue("id", options.getId());
		orderedMapSqlParameterSource.addValue("requester_user_id", options.getRequesterUserId());
		orderedMapSqlParameterSource.addValue("accepter_user_id", options.getAccepterUserId());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;
	}

}
