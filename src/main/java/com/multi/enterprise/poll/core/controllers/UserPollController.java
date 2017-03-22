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
import com.multi.enterprise.poll.core.controllers.converters.UserPollConverter;
import com.multi.enterprise.poll.core.service.UserPollService;
import com.multi.enterprise.types.exception.ServiceException;
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
	UserPollConverter converter;

	@Autowired
	UserPollService userPollService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserPollDTO create(@RequestBody final UserPollDTO userPollDto) throws ServiceException {
		UserPoll userPoll = this.converter.internalize(userPollDto);
		this.userPollService.create(userPoll);
		return userPollDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public UserPollDTO getById(String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public UserPollDTO update(@RequestBody final UserPollDTO userPollDto) throws ServiceException {
		final UserPoll userPoll = this.converter.internalize(userPollDto);
		this.userPollService.update(userPoll);
		return userPollDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

}
