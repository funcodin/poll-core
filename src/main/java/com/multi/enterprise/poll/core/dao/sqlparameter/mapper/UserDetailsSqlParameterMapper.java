/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.accounts.UserDetails;

/**
 * @author Robot
 *
 */
@Repository
public class UserDetailsSqlParameterMapper extends BaseSqlParameterMapper<UserDetails> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "user_details";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return "id";
	}

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final UserDetails userDetails) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("id", userDetails.getId());
		orderedMapSqlParameterSource.addValue("user_id", userDetails.getUserId());
		orderedMapSqlParameterSource.addValue("age_group", Objects.isNull(userDetails.getAgeGroup()) ? null
				: userDetails.getAgeGroup().name());
		orderedMapSqlParameterSource.addValue("gender", Objects.isNull(userDetails.getGender()) ? null : userDetails
				.getGender().name());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;
	}

}
