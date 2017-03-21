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

	private final String CREATE_USER_POLL = "insert into user_poll set() values ()";

	@Override
	public Class<UserPoll> getDocumentClass() {
		return UserPoll.class;
	}

}
