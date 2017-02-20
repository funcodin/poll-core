/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.accounts.User;
import com.multi.enterprise.types.poll.accounts.UserDetails;

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
		user.setUserDetails(new UserDetails());

		return user;
	}

}
