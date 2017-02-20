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
import com.multi.enterprise.types.poll.accounts.SecureUser;

/**
 * @author Robot
 *
 */

@Repository
public class SecureUserDao extends BaseJdbcRecordAccess<SecureUser> {

	private String SELECT_BY_USERID = "select * from secure_user where user_id = :user_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<SecureUser> getDocumentClass() {
		return SecureUser.class;
	}

	public SecureUser getByUserId(final String userId) {
		final List<SecureUser> secureUserList = this.jdbcTempalte.query(SELECT_BY_USERID,
				this.mapParams("user_id", userId), this.rowMapper);
		return CollectionUtils.isEmpty(secureUserList) ? null : secureUserList.get(0);
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
