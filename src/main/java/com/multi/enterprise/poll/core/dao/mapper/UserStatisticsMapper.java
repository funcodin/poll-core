/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.accounts.UserStatistics;

/**
 * @author Robot
 *
 */

@Repository
public class UserStatisticsMapper extends BaseRowMapper<UserStatistics> {

	@Override
	public UserStatistics mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final UserStatistics userStats = new UserStatistics();
		userStats.setUserId(resultSet.getString("user_id"));
		userStats.setQuestionsAnswered(resultSet.getInt("questions_answered"));
		userStats.setQuestionsAsked(resultSet.getInt("questions_asked"));
		userStats.setCommentCount(resultSet.getInt("user_comments"));
		userStats.setCreatedDate(resultSet.getDate("created_date"));
		userStats.setModifiedDate(resultSet.getDate("modified_date"));
		return userStats;
	}

}
