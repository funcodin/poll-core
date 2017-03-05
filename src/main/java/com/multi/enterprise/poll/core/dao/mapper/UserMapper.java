/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.accounts.AgeGroup;
import com.multi.enterprise.types.poll.accounts.Gender;
import com.multi.enterprise.types.poll.accounts.User;
import com.multi.enterprise.types.poll.accounts.UserDetails;
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;

/**
 * @author Robot
 *
 */
@Repository
public class UserMapper extends BaseRowMapper<User> {

	public User mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final User user = new User();
		user.setId(resultSet.getString("id"));
		user.setUserName(resultSet.getString("user_name"));
		user.setPassword(resultSet.getString("password"));
		user.setCreatedDate(resultSet.getDate("created_date"));
		user.setModifiedDate(resultSet.getDate("modified_date"));

		final UserDetails userDetails = new UserDetails();
		userDetails.setUserId(resultSet.getString("id"));
		userDetails.setGender(Objects.isNull(resultSet.getString("gender")) ? null : Gender.valueOf(resultSet
				.getString("gender")));
		userDetails.setAgeGroup(Objects.isNull(resultSet.getString("age_group")) ? null : AgeGroup.valueOf(resultSet
				.getString("age_group")));

		final UserPersonalDetails personalDetails = new UserPersonalDetails();
		personalDetails.setUserId(resultSet.getString("id"));
		personalDetails.setFullName(resultSet.getString("full_name"));
		personalDetails.setEmailAddress(resultSet.getString("email"));
		personalDetails.setContactNumber(resultSet.getString("contact"));

		user.setUserDetails(userDetails);
		user.setPersonalDetails(personalDetails);
		return user;
	}

}
