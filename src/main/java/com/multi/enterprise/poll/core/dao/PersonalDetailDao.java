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
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;

/**
 * @author Robot
 *
 */
@Repository
public class PersonalDetailDao extends BaseJdbcRecordAccess<UserPersonalDetails> {

	private String SELECT_BY_USERID = "select * from user_personal_info where user_id = :user_id";

	private String UPDATE_PERSONAL_DETAIL = "update user_personal_info set full_name=:full_name, email=:email, contact= :contact where user_id = :user_id";

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

	@Override
	public UserPersonalDetails update(final UserPersonalDetails personalDetails) {

		final Map<String, Object> param = new HashMap<>();
		param.put("user_id", personalDetails.getUserId());
		param.put("full_name", Objects.isNull(personalDetails.getFullName()) ? null : personalDetails.getFullName());
		param.put("email", Objects.isNull(personalDetails.getEmailAddress()) ? null : personalDetails.getEmailAddress());
		param.put("contact",
				Objects.isNull(personalDetails.getContactNumber()) ? null : personalDetails.getContactNumber());
		this.jdbcTempalte.update(UPDATE_PERSONAL_DETAIL, new MapSqlParameterSource(param));
		return personalDetails;

	}
}
