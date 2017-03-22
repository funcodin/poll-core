/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.QuestionConverter;
import com.multi.enterprise.poll.core.controllers.converters.UserPollConverter;
import com.multi.enterprise.poll.core.service.UserPollService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionDTO;
import com.multi.enterprise.types.poll.UserPoll;
import com.multi.enterprise.types.poll.UserPollDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */

@RestController
@RequestMapping(value = PollCoreRestEndpoints.USER_POLLS)
public class UserPollController implements CrudController<UserPollDTO> {

	@Autowired
	UserPollService userPollService;

	@Autowired
	UserPollConverter converter;

	@Autowired
	QuestionConverter questionConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public QuestionDTO createPoll(@RequestBody final UserPollDTO userPollDto) throws ServiceException {
		final UserPoll userPoll = this.converter.internalize(userPollDto);
		final Question question = this.userPollService.createPoll(userPoll);
		return this.questionConverter.externalize(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public UserPollDTO getById(final String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public UserPollDTO update(final UserPollDTO userPollDto) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public QuestionDTO updatePoll(@RequestBody final UserPollDTO userPollDto) throws ServiceException {
		final UserPoll userPoll = this.converter.internalize(userPollDto);
		final Question question = this.userPollService.updatePoll(userPoll);
		return this.questionConverter.externalize(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	public void delete(final String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	public UserPollDTO create(final UserPollDTO create) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

}
