/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.multi.enterprise.types.friends.FriendRequest;
import com.multi.enterprise.types.friends.FriendRequestDTO;

/**
 * @author Robot
 *
 */

@Component
public class FriendRequestConverter implements Converter<FriendRequest, FriendRequestDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public FriendRequest internalize(FriendRequestDTO friendRequestDto) {
		final FriendRequest friendRequest = new FriendRequest();
		friendRequest.setId(friendRequestDto.getFriendRequestId() == null ? UUID.randomUUID().toString()
				: friendRequestDto.getFriendRequestId());
		friendRequest.setRequesterUserId(friendRequestDto.getRequesterUserId());
		friendRequest.setAccepterUserId(friendRequestDto.getAccepterUserId());
		return friendRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public FriendRequestDTO externalize(FriendRequest friendRequest) {
		final FriendRequestDTO friendRequestDto = new FriendRequestDTO();
		friendRequestDto.setRequesterUserId(friendRequest.getId());
		friendRequestDto.setAccepterUserId(friendRequest.getAccepterUserId());
		friendRequestDto.setRequesterUserId(friendRequest.getRequesterUserId());
		return friendRequestDto;
	}

}
