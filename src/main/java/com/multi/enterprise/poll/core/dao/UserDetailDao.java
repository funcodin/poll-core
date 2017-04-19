/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

	private String UPDATE_USER_DETAILS = "update user_details set gender = :gender, age_group = :age_group where user_id = :user_id";

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
		this.jdbcTempalte.update(UPDATE_USER_DETAILS, this.mapParams("user_id", userDetails.getUserId(), "gender",
				Objects.isNull(userDetails.getGender()) ? null : userDetails.getGender().name(), "age_group",
				Objects.isNull(userDetails.getAgeGroup()) ? null : userDetails.getAgeGroup().name()));
		return userDetails;
	}

	protected MapSqlParameterSource mapParams(final String columnName1, final String columnValue1,
			final String columnName2, final String columnValue2, final String columnName3, final String columnValue3) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName1, columnValue1);
		param.put(columnName2, columnValue2);
		param.put(columnName3, columnValue3);
		return new MapSqlParameterSource(param);
	}

	protected MapSqlParameterSource mapParams(final String columnName1, final String columnValue1) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName1, columnValue1);
		return new MapSqlParameterSource(param);
	}

}
