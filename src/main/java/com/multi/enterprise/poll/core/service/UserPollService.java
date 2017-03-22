/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.UserPollDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */
@Service
public class UserPollService extends BaseRecordService<UserPoll> {

	@Autowired
	UserPollDao userPollDao;

	@Autowired
	QuestionService questionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<UserPoll> entityClass() {
		return UserPoll.class;
	}

	public Question createPoll(final UserPoll userPoll) throws ServiceException {
		this.userPollDao.createPoll(userPoll);
		return this.questionService.getById(userPoll.getQuestionId());
	}

	public Question updatePoll(final UserPoll userPoll) throws ServiceException {
		this.userPollDao.updatePoll(userPoll);
		return this.questionService.getById(userPoll.getQuestionId());
	}

}
