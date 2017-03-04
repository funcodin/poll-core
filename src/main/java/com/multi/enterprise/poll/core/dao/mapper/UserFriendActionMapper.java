/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.friends.UserFriendAction;

/**
 * @author Robot
 *
 */
@Repository
public class UserFriendActionMapper extends BaseRowMapper<UserFriendAction> {

	@Override
	public UserFriendAction mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final UserFriendAction userFriend = new UserFriendAction();
		userFriend.setAccepterUserId(resultSet.getString("user_id"));
		userFriend.setRequesterUserId(resultSet.getString("friends_id"));
		userFriend.setCreatedDate(resultSet.getDate("created_date"));
		userFriend.setModifiedDate(resultSet.getDate("modified_date"));

		return userFriend;
	}

}
