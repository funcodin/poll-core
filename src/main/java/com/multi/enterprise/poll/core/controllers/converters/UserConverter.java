/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.accounts.AgeGroup;
import com.multi.enterprise.types.poll.accounts.Gender;
import com.multi.enterprise.types.poll.accounts.User;
import com.multi.enterprise.types.poll.accounts.UserDetails;
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;
import com.multi.enterprise.types.users.UserDTO;

/**
 * @author Robot
 *
 */
@Component
public class UserConverter implements Converter<User, UserDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public User internalize(final UserDTO userDto) {
		final User user = new User();
		user.setId(StringUtils.isEmpty(userDto.getUserId()) ? UUID.randomUUID().toString() : userDto.getUserId());
		user.setUserName(userDto.getUsername());
		user.setPassword(userDto.getPassword());

		final UserDetails userDetails = new UserDetails();
		userDetails.setId(UUID.randomUUID().toString());
		userDetails.setAgeGroup(AgeGroup.valueOf(userDto.getAgeGroup()));
		userDetails.setGender(Gender.valueOf(userDto.getGender()));
		userDetails.setUserId(user.getUserId());

		final UserPersonalDetails personalDetails = new UserPersonalDetails();
		personalDetails.setId(UUID.randomUUID().toString());
		personalDetails.setUserId(user.getUserId());
		personalDetails.setFullName(userDto.getFullName());
		personalDetails.setEmailAddress(userDto.getEmail());
		personalDetails.setContactNumber(userDto.getContactNumber());

		user.setUserDetails(userDetails);
		user.setPersonalDetails(personalDetails);
		return user;
	}

	public User internalizeValidate(final UserDTO userDto) {
		final User user = new User();
		user.setUserName(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public UserDTO externalize(final User user) {
		final UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getId());
		userDto.setUsername(user.getUserName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}