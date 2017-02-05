/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.QuestionDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Service
public class QuestionService extends BaseRecordService<Question> {

	@Autowired
	QuestionDao questionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.service.EntityService#entityClass()
	 */
	public Class<Question> entityClass() {
		return Question.class;
	}

	@Override
	public Question create(final Question question) throws ServiceException {
		question.setId(UUID.randomUUID().toString());
		super.create(question);
		return question;
	}

}
