/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */
@Repository
public class UserPollMapper extends BaseRowMapper<UserPoll> {

	@Override
	public UserPoll mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final UserPoll userPoll = new UserPoll();
		userPoll.setQuestionId(resultSet.getString("question_id"));
		userPoll.setOptionId(resultSet.getString("option_id"));
		userPoll.setUserId(resultSet.getString("user_id"));
		userPoll.setCreatedDate(resultSet.getDate("created_date"));
		userPoll.setModifiedDate(resultSet.getDate("modified_date"));
		return userPoll;
	}

}
