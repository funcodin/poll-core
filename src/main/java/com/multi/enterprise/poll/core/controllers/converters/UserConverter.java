/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.Objects;
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
		userDetails.setAgeGroup(StringUtils.isEmpty(userDto.getAgeGroup()) ? null : AgeGroup.valueOf(userDto
				.getAgeGroup()));
		userDetails.setGender(StringUtils.isEmpty(userDto.getGender()) ? null : Gender.valueOf(userDto.getGender()));
		userDetails.setUserId(user.getUserId());

		final UserPersonalDetails personalDetails = new UserPersonalDetails();
		personalDetails.setId(UUID.randomUUID().toString());
		personalDetails.setUserId(user.getUserId());
		personalDetails.setFullName(userDto.getFullName());
		personalDetails.setEmailAddress(userDto.getEmail());
		personalDetails.setContactNumber(StringUtils.isEmpty(userDto.getContactNumber()) ? null : userDto
				.getContactNumber());

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
		userDto.setPassword("");

		final UserPersonalDetails personalDetails = user.getPersonalDetails();

		userDto.setFullName(personalDetails.getFullName());
		userDto.setContactNumber(personalDetails.getContactNumber());
		userDto.setEmail(personalDetails.getEmailAddress());

		final UserDetails userDetails = user.getUserDetails();
		userDto.setAgeGroup(Objects.nonNull(userDetails.getAgeGroup()) ? userDetails.getAgeGroup().name() : null);
		userDto.setGender(Objects.nonNull(userDetails.getGender()) ? userDetails.getGender().name() : null);

		return userDto;
	}

}
