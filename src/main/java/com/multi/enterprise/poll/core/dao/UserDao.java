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

	private String SELECT_BY_USER_NAME = "select a.id, a.user_name, a.password, a.created_date, a.modified_date, b.age_group, b.gender, c.full_name, c.email, c.contact "
			+ " from user a"
			+ " inner join user_details b on a.id = b.user_id"
			+ " inner join user_personal_info c on a.id = c.user_id" + " where a.user_name = :user_name";

	private String SELECT_BY_USERID = "select a.id, a.user_name, a.password, a.created_date, a.modified_date, b.age_group, b.gender, c.full_name, c.email, c.contact"
			+ " from user a"
			+ " inner join user_details b on a.id = b.user_id"
			+ " inner join user_personal_info c on a.id = c.user_id" + " where a.id = :id";

	private String UPDATE_PASSWORD = "update user set password = '%s' where id = :id";

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

	public User getByUserId(final String userId) {
		final List<User> users = this.jdbcTempalte
				.query(SELECT_BY_USERID, this.mapParams("id", userId), this.rowMapper);
		return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}

	public User update(final User user) {
		final String query = String.format(UPDATE_PASSWORD, user.getPassword());
		this.jdbcTempalte.update(query, this.mapParams("id", user.getUserId()));
		return user;
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
