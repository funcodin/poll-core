/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.friends.UserFriendAction;

/**
 * @author Robot
 *
 */

@Repository
public class UserFriendActionDao extends BaseJdbcRecordAccess<UserFriendAction> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	// private static final String ADD_FRIEND =
	// "insert into user_friends (user_id, friends_id) values (:user_id, :friends_id)";
	private static final String DELETE_FRIEND = "delete from user_friends where user_id = :user_id and friends_id = :friends_id";

	@Override
	public Class<UserFriendAction> getDocumentClass() {
		return UserFriendAction.class;
	}

	public void addFriend(final String accepterUserId, final String friendUserId) {
		super.create(this.createUserFriend(accepterUserId, friendUserId));
		super.create(this.createUserFriend(friendUserId, accepterUserId));
		// this.jdbcTempalte.query(ADD_FRIEND, this.mapParams("user_id", accepterUserId, "friends_id", friendUserId),
		// this.rowMapper);

		// this.jdbcTempalte.query(ADD_FRIEND, this.mapParams("user_id", friendUserId, "friends_id", accepterUserId),
		// this.rowMapper);
	}

	public void deleteFriend(final String accepterUserId, final String friendUserId) {
		this.jdbcTempalte.query(DELETE_FRIEND, this.mapParams("user_id", accepterUserId, "friends_id", friendUserId),
				this.rowMapper);

		this.jdbcTempalte.query(DELETE_FRIEND, this.mapParams("user_id", friendUserId, "friends_id", accepterUserId),
				this.rowMapper);

	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue,
			final String columnName1, final String columnValue1) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		param.put(columnName1, columnValue1);
		return new MapSqlParameterSource(param);
	}

	private UserFriendAction createUserFriend(final String userId, final String friendId) {
		final UserFriendAction userFriend = new UserFriendAction();
		userFriend.setAccepterUserId(userId);
		userFriend.setRequesterUserId(friendId);
		return userFriend;
	}

}
