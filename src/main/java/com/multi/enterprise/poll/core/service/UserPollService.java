/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.UserPollDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */
@Service
public class UserPollService extends BaseRecordService<UserPoll> {

	@Autowired
	UserPollDao userPollDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<UserPoll> entityClass() {
		return UserPoll.class;
	}

	@Override
	public UserPoll create(final UserPoll userPoll) throws ServiceException {
		return this.userPollDao.createPoll(userPoll);
	}

	@Override
	public UserPoll update(final UserPoll userPoll) throws ServiceException {
		return this.userPollDao.updatePoll(userPoll);
	}

}
