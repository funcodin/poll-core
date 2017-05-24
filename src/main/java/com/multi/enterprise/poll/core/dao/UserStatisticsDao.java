/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.accounts.UserStatistics;

/**
 * @author Robot
 *
 */
@Repository
public class UserStatisticsDao extends BaseJdbcRecordAccess<UserStatistics> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	protected static final String INCREMENT_QUESTIONS_ANSWERED = "update user_statistics set questions_answered = :questions_answered where user_id = :user_id";
	protected static final String INCREMENT_QUESTIONS_ASKED = "update user_statistics set questions_asked = :questions_asked where user_id = :user_id";
	protected static final String GET_USER_STATS = "select * from user_statistics where user_id = :user_id";

	@Override
	public Class<UserStatistics> getDocumentClass() {
		return UserStatistics.class;
	}

	public UserStatistics incrementVotedCount(final String userId) {
		final UserStatistics userStats = this.getById(userId);
		if (Objects.isNull(userStats)) {

		}
		userStats.setQuestionsAnswered(userStats.getQuestionsAnswered() + 1);
		final UserStatistics updatedStats = this.update(userStats);
		return updatedStats;
	}

	public UserStatistics incrementAskedCount(final String userId) {
		final UserStatistics userStats = this.getById(userId);
		userStats.setQuestionsAsked(userStats.getQuestionsAsked() + 1);
		final UserStatistics updateStats = this.update(userStats);
		return updateStats;

	}

	public UserStatistics incrementCommentCount(final String userId) {
		final UserStatistics userStats = this.getById(userId);
		userStats.setCommentCount(userStats.getCommentCount() + 1);
		final UserStatistics updateStats = this.update(userStats);
		return updateStats;
	}

	public UserStatistics createInitialUserStats(final String userId) {
		final UserStatistics userStats = new UserStatistics();
		userStats.setUserId(userId);
		userStats.setQuestionsAnswered(0);
		userStats.setQuestionsAsked(0);
		userStats.setCommentCount(0);

		return this.create(userStats);
	}

}
