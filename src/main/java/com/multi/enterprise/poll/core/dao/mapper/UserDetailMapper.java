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
import com.multi.enterprise.types.poll.accounts.UserDetails;

/**
 * @author Robot
 *
 */
@Repository
public class UserDetailMapper extends BaseRowMapper<UserDetails> {

	@Override
	public UserDetails mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final UserDetails userDetails = new UserDetails();
		userDetails.setId(resultSet.getString("id"));
		userDetails.setUserId(resultSet.getString("user_id"));
		userDetails.setGender(Objects.isNull(resultSet.getString("gender")) ? null : Gender.valueOf(resultSet
				.getString("gender")));
		userDetails.setAgeGroup(Objects.isNull(resultSet.getString("age_group")) ? null : AgeGroup.valueOf(resultSet
				.getString("age_group")));
		userDetails.setCreatedDate(resultSet.getDate("created_date"));
		userDetails.setModifiedDate(resultSet.getDate("modified_date"));

		return userDetails;
	}

}
