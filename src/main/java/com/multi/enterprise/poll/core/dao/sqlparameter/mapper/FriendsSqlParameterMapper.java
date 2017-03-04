/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.friends.Friend;

/**
 * @author Robot
 *
 */
@Repository
public class FriendsSqlParameterMapper extends BaseSqlParameterMapper<Friend> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	@Override
	public String getTableName() {
		// this is not supported
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	@Override
	public String getIdColumnName() {
		return null;
	}

}
