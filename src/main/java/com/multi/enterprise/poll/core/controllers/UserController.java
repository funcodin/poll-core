/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.QuestionListConverter;
import com.multi.enterprise.poll.core.controllers.converters.UserConverter;
import com.multi.enterprise.poll.core.service.UserService;
import com.multi.enterprise.types.exception.ClientException;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.QuestionListDTO;
import com.multi.enterprise.types.poll.accounts.User;
import com.multi.enterprise.types.poll.accounts.UserReset;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;
import com.multi.enterprise.types.users.UserDTO;
import com.multi.enterprise.types.users.UserResetDTO;

/**
 * @author Robot
 *
 */
@RestController
@RequestMapping(value = PollCoreRestEndpoints.USER)
public class UserController implements CrudController<UserDTO> {

	@Autowired
	UserConverter converter;

	@Autowired
	UserService userService;

	@Autowired
	QuestionListConverter questionListConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserDTO create(@RequestBody final UserDTO userDto) throws ServiceException {
		final User user = this.converter.internalize(userDto);
		final User createUser = this.userService.create(user);
		return this.converter.externalize(createUser);
	}

	// This is coz I couldnt figure out angular 2 issue
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public UserDTO updateUser(@RequestBody final UserDTO update) throws ServiceException {
		if (StringUtils.isEmpty(update.getUserId())) {
			throw new ServiceException(" User Id is required ");
		}
		final User user = this.converter.internalize(update);
		final User updatedUser = this.userService.update(user);
		return this.converter.externalize(updatedUser);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public QuestionListDTO validateUser(@RequestBody final UserDTO userDto) throws ServiceException, ClientException {
		final User user = this.converter.internalizeValidate(userDto);
		return this.questionListConverter.externalize(this.userService.validate(user));
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDTO loginUser(@RequestBody final UserDTO userDto) throws ServiceException {
		final User user = this.converter.internalizeValidate(userDto);
		return this.converter.externalize(this.userService.login(user));
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public UserResetDTO resetPassword(@RequestBody final UserResetDTO userResetDto) throws ServiceException,
			ClientException {
		final UserReset userReset = this.converter.internalizeValidate(userResetDto);
		final UserReset updatedUserReset = this.userService.resetPasswordByEmail(userReset);
		return this.converter.externalize(updatedUserReset);
	}

	@RequestMapping(value = "/forgotUsername", method = RequestMethod.POST)
	public UserResetDTO forgotUsername(@RequestBody final UserResetDTO userResetDto) throws ServiceException,
			ClientException {
		final UserReset userReset = this.converter.internalizeValidate(userResetDto);
		final UserReset updatedUserReset = this.userService.forgotUsername(userReset);
		return this.converter.externalize(updatedUserReset);
	}

	@RequestMapping(value = "/forgotUsernamePassword", method = RequestMethod.POST)
	public UserResetDTO forgotUsernamePassword(@RequestBody final UserResetDTO userResetDto) throws ServiceException,
			ClientException {
		final UserReset userReset = this.converter.internalizeValidate(userResetDto);
		final UserReset updatedUserReset = this.userService.forgotUsernamePassword(userReset);
		return this.converter.externalize(updatedUserReset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO getById(@PathVariable final String id) throws ServiceException {
		final User user = this.userService.getUserById(id);
		return this.converter.externalize(user);
	}

	@RequestMapping(value = "/userName/{userName}", method = RequestMethod.GET)
	public UserDTO getByUserName(@PathVariable final String userName) throws ServiceException, ClientException {
		final User user = this.userService.getByUserName(userName);
		return this.converter.externalize(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public UserDTO update(@RequestBody UserDTO update) throws ServiceException {
		if (StringUtils.isEmpty(update.getUserId())) {
			throw new ServiceException(" User Id is required ");
		}
		final User user = this.converter.internalize(update);
		final User updatedUser = this.userService.update(user);
		return this.converter.externalize(updatedUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
