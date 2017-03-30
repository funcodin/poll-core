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
import com.multi.enterprise.types.poll.accounts.UserDetails;

/**
 * @author Robot
 *
 */
@Repository
public class UserDetailDao extends BaseJdbcRecordAccess<UserDetails> {

	private String SELECT_BY_USERID = "select * from user_details where user_id = :user_id";

	private String UPDATE_USER_DETAILS = "update user_details set gender ='%s', age_group='%s' where user_id = :user_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<UserDetails> getDocumentClass() {
		return UserDetails.class;
	}

	public UserDetails getByUserId(final String userId) {
		final List<UserDetails> userDetailList = this.jdbcTempalte.query(SELECT_BY_USERID,
				this.mapParams("user_id", userId), this.rowMapper);
		return CollectionUtils.isEmpty(userDetailList) ? null : userDetailList.get(0);
	}

	@Override
	public UserDetails update(final UserDetails userDetails) {
		final String query = String.format(UPDATE_USER_DETAILS, userDetails.getGender().name(), userDetails
				.getAgeGroup().name());
		this.jdbcTempalte.update(query, this.mapParams("user_id", userDetails.getUserId()));
		return userDetails;
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
