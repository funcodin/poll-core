/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.OptionsDao;
import com.multi.enterprise.poll.core.dao.QuestionDao;
import com.multi.enterprise.poll.core.validation.QuestionValidation;
import com.multi.enterprise.types.exception.EntityNotFoundException;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionList;

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
	public Question getById(final String id) throws ServiceException {
		final Question question = this.questionDao.getById(id);
		final List<Options> options = this.optionsDao.getAllOptionsByQuestionId(id);
		question.setTotalVotes(options.stream().mapToInt(option -> option.getVoteCount()).sum());
		question.setOptions(options);
		return question;
	}

	@Override
	public Question create(final Question question) throws ServiceException {
		QuestionValidation.isValidQuestion(question);
		question.setTotalVotes(0);
		super.create(question);

		for (final Options options : question.getOptions()) {
			options.setVoteCount(0);
			optionsDao.create(options);
		}
		return question;
	}

	@Override
	public void delete(String id) throws ServiceException {
		final Question question = this.questionDao.getById(id);

		if (Objects.isNull(question)) {
			throw new EntityNotFoundException(String.format(" Question not found for id %s", id));
		}

		for (final Options option : question.getOptions()) {
			this.optionsDao.delete(option);
		}
		this.questionDao.delete(question);
	}

	public QuestionList getPaginatedQuestion(final int lastQuestionIndex, final int limit) {
		final QuestionList questionList = this.questionDao.getPaginatedQuestion(lastQuestionIndex, limit);

		for (Question question : questionList.getQuestions()) {
			final List<Options> options = this.optionsDao.getAllOptionsByQuestionId(question.getId());
			question.setTotalVotes(options.stream().mapToInt(option -> option.getVoteCount()).sum());
			question.setOptions(options);
		}
		return questionList;
	}

}
