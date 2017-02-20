/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.accounts.SecureUser;

/**
 * @author Robot
 *
 */
@Repository
public class SecureUserSqlParameterMapper extends BaseSqlParameterMapper<SecureUser> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		return "secure_user";
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
	public OrderedMapSqlParameterSource mapInsertSqlParams(final SecureUser secureUser) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();
		orderedMapSqlParameterSource.addValue("id", secureUser.getId());
		orderedMapSqlParameterSource.addValue("user_id", secureUser.getUserId());
		orderedMapSqlParameterSource.addValue("salt", secureUser.getSalt());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;
	}

}
