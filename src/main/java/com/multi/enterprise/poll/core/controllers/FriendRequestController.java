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
import com.multi.enterprise.poll.core.controllers.converters.FriendRequestConverter;
import com.multi.enterprise.poll.core.service.FriendRequestService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.friends.FriendRequest;
import com.multi.enterprise.types.friends.FriendRequestDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */
@RestController
@RequestMapping(value = PollCoreRestEndpoints.FRIEND_REQUEST)
public class FriendRequestController implements CrudController<FriendRequestDTO> {

	@Autowired
	FriendRequestConverter converter;

	@Autowired
	FriendRequestService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public FriendRequestDTO create(@RequestBody FriendRequestDTO friendRequestDto) throws ServiceException {
		final FriendRequest friendRequest = this.converter.internalize(friendRequestDto);
		final FriendRequest createdFR = this.service.create(friendRequest);
		return this.converter.externalize(createdFR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public FriendRequestDTO getById(String id) throws ServiceException {
		throw new ServiceException("Operation not supported");
	}

	// TODO
	/*
	 * Create FriendRequestList DTO Add method to return list of pending FR by userId
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public FriendRequestDTO update(FriendRequestDTO update) throws ServiceException {
		throw new ServiceException("Operation not supported");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{requesterId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String requesterId) throws ServiceException {
		System.out.println("deleting requseterId " + requesterId);
		this.service.delete(requesterId);
	}

}
