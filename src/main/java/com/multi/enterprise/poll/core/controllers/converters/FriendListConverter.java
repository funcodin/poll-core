/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.friends.Friend;
import com.multi.enterprise.types.friends.FriendList;
import com.multi.enterprise.types.friends.FriendsDTO;
import com.multi.enterprise.types.users.UserDTO;

/**
 * @author Robot
 *
 */
@Component
public class FriendListConverter implements Converter<FriendList, FriendsDTO> {

	@Autowired
	UserConverter userConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */

	@Override
	public FriendList internalize(FriendsDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public FriendsDTO externalize(FriendList entity) {
		final FriendsDTO friendsDto = new FriendsDTO();
		final List<UserDTO> userDto = new ArrayList<>();

		for (final Friend friend : entity.getUserFriends()) {
			userDto.add(this.userConverter.externalize(friend));
		}

		friendsDto.setTotalFriends(entity.getTotalFriends());
		friendsDto.setFriends(userDto);
		return friendsDto;
	}
}
