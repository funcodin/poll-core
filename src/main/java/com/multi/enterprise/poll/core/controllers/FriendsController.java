/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.FriendListConverter;
import com.multi.enterprise.poll.core.service.FriendsService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.friends.FriendList;
import com.multi.enterprise.types.friends.FriendsDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */
@RestController
@RequestMapping(value = PollCoreRestEndpoints.FRIENDS)
public class FriendsController implements CrudController<FriendsDTO> {

	@Autowired
	FriendListConverter converter;

	@Autowired
	FriendsService friendsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	public FriendsDTO create(FriendsDTO create) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public FriendsDTO getById(@PathVariable final String id) throws ServiceException {
		final FriendList friends = this.friendsService.getUserFriends(id);
		return this.converter.externalize(friends);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public FriendsDTO update(FriendsDTO update) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
