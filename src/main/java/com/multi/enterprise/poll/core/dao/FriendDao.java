/**
 *
 */
package com.multi.enterprise.poll.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.friends.Friend;
import com.multi.enterprise.types.friends.FriendList;

/**
 * @author Robot
 *
 */
@Repository
public class FriendDao extends BaseJdbcRecordAccess<Friend> {

	private final String GET_USER_FRIENDS = "select a.friends_id, b.user_name, c.full_name, c.email, c.contact, d.age_group, d.gender"
			+ " from user_friends a "
			+ " inner join user b on a.friends_id = b.id"
			+ " inner join user_personal_info c on a.friends_id = c.user_id"
			+ " inner join user_details d on a.friends_id = d.user_id" + " where a.user_id = :user_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<Friend> getDocumentClass() {
		return Friend.class;
	}

	public FriendList getFriendsByUserId(final String userId) {

		final List<Friend> friends = this.jdbcTempalte.query(GET_USER_FRIENDS, this.mapParams("user_id", userId),
				this.rowMapper);

		final FriendList friendList = new FriendList();
		friendList.setTotalFriends(friends.size());
		friendList.setUserFriends(friends);

		return friendList;

	}

	// delete Friends

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}
}
