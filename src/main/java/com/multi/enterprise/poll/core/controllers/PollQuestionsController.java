/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.QuestionConverter;
import com.multi.enterprise.poll.core.controllers.converters.QuestionListConverter;
import com.multi.enterprise.poll.core.service.QuestionService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionDTO;
import com.multi.enterprise.types.poll.QuestionList;
import com.multi.enterprise.types.poll.QuestionListDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */

@RestController
@RequestMapping(value = PollCoreRestEndpoints.QUESTION)
public class PollQuestionsController implements CrudController<QuestionDTO> {

	@Autowired
	QuestionConverter converter;

	@Autowired
	QuestionListConverter questionListConverter;

	@Autowired
	QuestionService questionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public QuestionDTO create(@RequestBody QuestionDTO questionDto) throws ServiceException {
		final Question question = this.converter.internalize(questionDto);
		final Question createdQuestion = this.questionService.create(question);
		return this.converter.externalize(createdQuestion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public QuestionDTO getById(@PathVariable final String id) throws ServiceException {
		final Question question = this.questionService.getById(id);
		return this.converter.externalize(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public QuestionDTO update(@RequestBody QuestionDTO questionDto) throws ServiceException {
		final Question question = this.converter.internalize(questionDto);
		final Question updatedQuestion = this.questionService.update(question);
		return this.converter.externalize(updatedQuestion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(String id) throws ServiceException {
		this.questionService.delete(id);
	}

	@RequestMapping(value = "getPaginatedQuestion/{lastQuestionIndex}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getQuestions(@PathVariable final int lastQuestionIndex, @PathVariable final int limit)
			throws ServiceException {
		final QuestionList questionList = this.questionService.getPaginatedQuestion(lastQuestionIndex, limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "latest/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getLatestPaginationQuestions(@PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getLatestPaginatedQuestion(limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "latest/user/{userId}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getLatestPaginationQuestionsForUser(@PathVariable final String userId,
			@PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getLatestPaginatedQuestionForUser(userId, limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "getPaginatedQuestion/user/{userId}/lastQuestionIndex/{lastQuestionIndex}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getQuestionsForUser(@PathVariable final String userId,
			@PathVariable final int lastQuestionIndex, @PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getPaginatedQuestionForUser(userId, lastQuestionIndex,
				limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "latestByUser/user/{userId}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getLatestPaginationQuestionsByUser(@PathVariable final String userId,
			@PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getLatestPaginatedQuestionByUser(userId, limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "getPaginatedQuestionByUser/user/{userId}/lastQuestionIndex/{lastQuestionIndex}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getQuestionsByUser(@PathVariable final String userId,
			@PathVariable final int lastQuestionIndex, @PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getPaginatedQuestionByUser(userId, lastQuestionIndex,
				limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "latestVotedByUser/user/{userId}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getLatestPaginationQuestionsVotedByUser(@PathVariable final String userId,
			@PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getLatestPaginatedQuestionVotedByUser(userId, limit);
		return this.questionListConverter.externalize(questionList);
	}

	@RequestMapping(value = "getPaginatedQuestionVotedByUser/user/{userId}/lastQuestionIndex/{lastQuestionIndex}/limit/{limit}", method = RequestMethod.GET)
	public QuestionListDTO getQuestionsVotedByUser(@PathVariable final String userId,
			@PathVariable final int lastQuestionIndex, @PathVariable final int limit) throws ServiceException {
		final QuestionList questionList = this.questionService.getPaginatedQuestionVotedByUser(userId,
				lastQuestionIndex, limit);
		return this.questionListConverter.externalize(questionList);
	}

}
