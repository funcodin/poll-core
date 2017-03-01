/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.friends.FriendRequest;

/**
 * @author Robot
 *
 */

@Repository
public class FriendRequestMapper extends BaseRowMapper<FriendRequest> {

	@Override
	public FriendRequest mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final FriendRequest friendRequest = new FriendRequest();
		friendRequest.setId(resultSet.getString("id"));
		friendRequest.setRequesterUserId(resultSet.getString("requester_user_id"));
		friendRequest.setAccepterUserId(resultSet.getString("accepter_user_id"));
		friendRequest.setCreatedDate(resultSet.getDate("created_date"));
		friendRequest.setModifiedDate(resultSet.getDate("modified_date"));
		return friendRequest;

	}

}
