/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.UserPoll;

/**
 * @author Robot
 *
 */
@Repository
public class UserPollDao extends BaseJdbcRecordAccess<UserPoll> {

	@Autowired
	OptionsDao optionsDao;

	@Autowired
	UserStatisticsDao userStatDao;

	protected static final String GET_USER_POLL = "select user_id, option_id, question_id, created_date, modified_date from user_poll where user_id = :user_id and question_id = :question_id";

	protected static final String DELETE_USER_POLL = "delete from user_poll where user_id = :user_id and question_id = :question_id";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	@Override
	public Class<UserPoll> getDocumentClass() {
		return UserPoll.class;
	}

	public UserPoll createPoll(final UserPoll userPoll) throws ServiceException {
		boolean incrementStat = true;
		final UserPoll existingPoll = this.getUserPoll(userPoll);

		// Fail safe approach
		if (Objects.nonNull(existingPoll)) {
			this.deleteUserPoll(existingPoll);
			this.optionsDao.decrementVoteCount(existingPoll.getOptionId());
			incrementStat = false;
		}
		this.optionsDao.incrementVoteCount(userPoll.getOptionId());
		if (incrementStat) {
			this.userStatDao.incrementVotedCount(userPoll.getUserId());
		}
		return super.create(userPoll);

	}

	public UserPoll updatePoll(final UserPoll userPoll) {
		final UserPoll existingPoll = this.getUserPoll(userPoll);

		// Fail safe approach
		if (Objects.nonNull(existingPoll)) {
			this.deleteUserPoll(existingPoll);
			this.optionsDao.decrementVoteCount(existingPoll.getOptionId());
		}
		this.optionsDao.incrementVoteCount(userPoll.getOptionId());
		return super.create(userPoll);

	}

	public UserPoll getUserPoll(final UserPoll userPoll) {
		final List<UserPoll> userPolls = this.jdbcTempalte.query(GET_USER_POLL,
				this.mapParams("user_id", userPoll.getUserId(), "question_id", userPoll.getQuestionId()),
				this.rowMapper);
		return CollectionUtils.isEmpty(userPolls) ? null : userPolls.get(0);
	}

	public void deleteUserPoll(final UserPoll userPoll) {
		this.jdbcTempalte.update(DELETE_USER_POLL,
				this.mapParams("user_id", userPoll.getUserId(), "question_id", userPoll.getQuestionId()));
	}

	protected MapSqlParameterSource mapParams(final String columnName1, final String columnValue1,
			final String columnName2, final String columnValue2) {

		final Map<String, Object> param = new HashMap<>();
		param.put(columnName1, columnValue1);
		param.put(columnName2, columnValue2);
		return new MapSqlParameterSource(param);
	}

}
