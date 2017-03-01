/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.friends.FriendRequest;

/**
 * @author Robot
 *
 */

@Repository
public class FriendRequestDao extends BaseJdbcRecordAccess<FriendRequest> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	protected static final String GET_ALL_REQUEST_BY_USERID = "select * from pending_friend_request where accepter_user_id = :accepter_user_id";

	@Override
	public Class<FriendRequest> getDocumentClass() {
		return FriendRequest.class;
	}

	public List<FriendRequest> getPendingFriendRequestByUserId(final String userId) {

		final List<FriendRequest> pendingFriendRequests = this.jdbcTempalte.query(GET_ALL_REQUEST_BY_USERID,
				this.mapParams("accepter_user_id", userId), this.rowMapper);
		return CollectionUtils.isEmpty(pendingFriendRequests) ? new ArrayList<>() : pendingFriendRequests;
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
