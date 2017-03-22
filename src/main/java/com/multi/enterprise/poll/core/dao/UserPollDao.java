/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */
@Repository
public class UserPollDao extends BaseJdbcRecordAccess<UserPoll> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	@Override
	public Class<UserPoll> getDocumentClass() {
		return UserPoll.class;
	}

}
