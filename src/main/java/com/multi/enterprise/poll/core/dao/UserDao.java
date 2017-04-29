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
import com.multi.enterprise.types.exception.IllegalArgumentServiceException;
import com.multi.enterprise.types.exception.ServiceException;
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

	private String FIND_USER_BY_EMAIL = "select a.id, a.user_name, a.password, a.created_date, a.modified_date, b.age_group, b.gender, c.full_name, c.email, c.contact"
			+ " from user_personal_info c inner join user_details b on c.user_id = b.user_id"
			+ " inner join user a on c.user_id = a.id where c.email = :email";

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

	public User getByUserName(final String userName) throws IllegalArgumentServiceException {
		final List<User> users = this.jdbcTempalte.query(SELECT_BY_USER_NAME, this.mapParams("user_name", userName),
				this.rowMapper);
		if (CollectionUtils.isEmpty(users))
			throw new IllegalArgumentServiceException(String.format("User with username  %s not found ", userName));
		else
			return users.get(0);

	}

	public User getUserByEmail(final String email) throws ServiceException {
		final List<User> users = this.jdbcTempalte.query(FIND_USER_BY_EMAIL, this.mapParams("email", email),
				this.rowMapper);
		if (CollectionUtils.isEmpty(users))
			throw new ServiceException(String.format("User with email  %s not found", email));
		else
			return users.get(0);
	}

	public User getByUserId(final String userId) throws ServiceException {
		final List<User> users = this.jdbcTempalte
				.query(SELECT_BY_USERID, this.mapParams("id", userId), this.rowMapper);
		if (CollectionUtils.isEmpty(users))
			throw new ServiceException(String.format("User with userId  %s not found", userId));
		else
			return users.get(0);
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
