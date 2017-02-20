/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.accounts.SecureUser;

/**
 * @author Robot
 *
 */
@Repository
public class SecureUserMapper extends BaseRowMapper<SecureUser> {

	@Override
	public SecureUser mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final SecureUser secureUser = new SecureUser();
		secureUser.setId(resultSet.getString("id"));
		secureUser.setUserId(resultSet.getString("user_id"));
		secureUser.setSalt(resultSet.getBytes("salt"));
		secureUser.setCreatedDate(resultSet.getDate("created_date"));
		secureUser.setModifiedDate(resultSet.getDate("modified_date"));

		return secureUser;
	}

}
