/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.accounts.User;

/**
 * @author Robot
 *
 */

@Repository
public class UserDao extends BaseJdbcRecordAccess<User> {

	private String SELECT_BY_USER_NAME = "select * from user where user_name = :user_name";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<User> getDocumentClass() {
		return User.class;
	}

	public User getByUserName(final String userName) {
		final List<User> users = this.jdbcTempalte.query(SELECT_BY_USER_NAME, this.mapParams("user_name", userName),
				this.rowMapper);
		return CollectionUtils.isEmpty(users) ? null : users.get(0);

	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
