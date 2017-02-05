/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.OptionsDao;
import com.multi.enterprise.poll.core.dao.QuestionDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Service
public class QuestionService extends BaseRecordService<Question> {

	@Autowired
	QuestionDao questionDao;

	@Autowired
	OptionsDao optionsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.service.EntityService#entityClass()
	 */
	public Class<Question> entityClass() {
		return Question.class;
	}

	@Override
	public Question getById(String id) throws ServiceException {
		final Question question = this.questionDao.getById(id);
		List<Options> options = this.optionsDao.getAllOptionsByQuestionId(id);
		question.setOptions(options);
		return question;
	}

	@Override
	public Question create(final Question question) throws ServiceException {
		question.setId(UUID.randomUUID().toString());
		question.setTotalVotes(0);
		super.create(question);

		for (final Options options : question.getOptions()) {
			options.setId(UUID.randomUUID().toString());
			options.setQuestionId(question.getId());
			options.setVoteCount(0);
			optionsDao.create(options);
		}

		return question;
	}

}
