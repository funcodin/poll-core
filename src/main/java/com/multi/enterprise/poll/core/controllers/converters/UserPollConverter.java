/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.UserPoll;
import com.multi.enterprise.types.poll.UserPollDTO;

/**
 * @author Robot
 *
 */

@Component
public class UserPollConverter implements Converter<UserPoll, UserPollDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public UserPoll internalize(UserPollDTO userPollDto) throws ServiceException {
		final UserPoll userPoll = new UserPoll();
		if (StringUtils.isNotEmpty(userPollDto.getOptionId())) {
			userPoll.setOptionId(userPollDto.getOptionId());
		} else {
			throw new ServiceException(" OpitonId is required");
		}

		if (StringUtils.isNotEmpty(userPollDto.getQuestionId())) {
			userPoll.setQuestionId(userPollDto.getQuestionId());
		} else {
			throw new ServiceException(" Question is required");
		}

		if (StringUtils.isNotEmpty(userPollDto.getUserId())) {
			userPoll.setUserId(userPollDto.getUserId());
		} else {
			throw new ServiceException(" UserId is required");
		}

		return userPoll;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public UserPollDTO externalize(UserPoll entity) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
