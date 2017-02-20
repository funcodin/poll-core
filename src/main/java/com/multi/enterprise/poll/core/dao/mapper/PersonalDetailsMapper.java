/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;

/**
 * @author Robot
 *
 */
@Repository
public class PersonalDetailsMapper extends BaseRowMapper<UserPersonalDetails> {

	@Override
	public UserPersonalDetails mapRow(final ResultSet resultSet, final int rowNumber) throws SQLException {
		final UserPersonalDetails personalDetails = new UserPersonalDetails();
		personalDetails.setId(resultSet.getString("id"));
		personalDetails.setUserId(resultSet.getString("user_id"));
		personalDetails.setFullName(resultSet.getString("full_name"));
		personalDetails.setEmailAddress(resultSet.getString("email"));
		personalDetails.setContactNumber(resultSet.getString("contact"));
		personalDetails.setCreatedDate(resultSet.getDate("created_date"));
		personalDetails.setModifiedDate(resultSet.getDate("modified_date"));
		return personalDetails;
	}

}
