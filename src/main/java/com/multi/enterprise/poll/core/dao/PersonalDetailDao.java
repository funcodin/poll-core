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
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;

/**
 * @author Robot
 *
 */
@Repository
public class PersonalDetailDao extends BaseJdbcRecordAccess<UserPersonalDetails> {

	private String SELECT_BY_USERID = "select * from user_personal_info where user_id = :user_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<UserPersonalDetails> getDocumentClass() {
		return UserPersonalDetails.class;
	}

	public UserPersonalDetails getByUserId(final String userId) {
		final List<UserPersonalDetails> personalDetailList = this.jdbcTempalte.query(SELECT_BY_USERID,
				this.mapParams("user_id", userId), this.rowMapper);
		return CollectionUtils.isEmpty(personalDetailList) ? null : personalDetailList.get(0);
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}
}
