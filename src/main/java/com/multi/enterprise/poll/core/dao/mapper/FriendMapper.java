/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.friends.Friend;
import com.multi.enterprise.types.poll.accounts.AgeGroup;
import com.multi.enterprise.types.poll.accounts.Gender;
import com.multi.enterprise.types.poll.accounts.UserDetails;
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;

/**
 * @author Robot
 *
 */

@Repository
public class FriendMapper extends BaseRowMapper<Friend> {

	@Override
	public Friend mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final Friend friend = new Friend();

		friend.setUserId(resultSet.getString("friends_id"));
		friend.setUserName(resultSet.getString("user_name"));

		final UserPersonalDetails personalDetails = new UserPersonalDetails();
		personalDetails.setFullName(resultSet.getString("full_name"));
		personalDetails.setEmailAddress(resultSet.getString("email"));
		personalDetails.setContactNumber(resultSet.getString("contact"));

		final UserDetails userDetails = new UserDetails();
		userDetails.setGender(Objects.isNull(resultSet.getString("gender")) ? null : Gender.valueOf(resultSet
				.getString("gender")));
		userDetails.setAgeGroup(Objects.isNull(resultSet.getString("age_group")) ? null : AgeGroup.valueOf(resultSet
				.getString("age_group")));

		friend.setUserDetails(userDetails);
		friend.setPersonalDetails(personalDetails);

		return friend;

	}

}
