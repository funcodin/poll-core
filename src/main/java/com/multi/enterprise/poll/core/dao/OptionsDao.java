/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.Options;

/**
 * @author Robot
 *
 */

@Repository
public class OptionsDao extends BaseJdbcRecordAccess<Options> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	protected static final String WHERE_CLAUSE = " WHERE id = :id";
	protected static final String GET_VOTE = "select %s from %s" + WHERE_CLAUSE;
	protected static final String ADD_VOTE = "update %s SET vote_count= :vote_count" + WHERE_CLAUSE;
	protected static final String GET_ALL_OPTIONS_BY_QUESTION_ID = "select * from options where question_id = :question_id";

	public Class<Options> getDocumentClass() {
		return Options.class;
	}

	private String getVoteCountQuery() {
		return String.format(GET_VOTE, "vote_count", this.getTable());
	}

	private String updateVoteCountQuery() {
		return String.format(ADD_VOTE, this.getTable());
	}

	private String getTable() {
		return "options";
	}

	public void incrementVoteCount(final String optionId) {
		final int updatedVoteCount = this.getVoteCount(optionId) + 1;
		this.updateVoteCount(optionId, updatedVoteCount);
	}

	public void decrementVoteCount(final String optionId) {

		final int voteCount = this.getVoteCount(optionId);
		if (voteCount <= 0) {
			return;
		}
		this.updateVoteCount(optionId, voteCount - 1);

	}

	public Integer getVoteCount(final String optionId) {
		return this.jdbcTempalte.query(this.getVoteCountQuery(), this.mapIdParameter(optionId),
				new ResultSetExtractor<Integer>() {
					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						return rs.next() ? rs.getInt("vote_count") : 0;
					}
				});
	}

	private void updateVoteCount(final String optionId, final int voteCount) {
		this.jdbcTempalte.update(this.updateVoteCountQuery(), this.mapIdandVoteParameter(optionId, voteCount));
	}

	public List<Options> getAllOptionsByQuestionId(final String questionId) {
		final List<Options> options = this.jdbcTempalte.query(GET_ALL_OPTIONS_BY_QUESTION_ID,
				this.mapParams("question_id", questionId), this.rowMapper);
		return options;

	}

	protected MapSqlParameterSource mapIdandVoteParameter(final String id, final int voteCount) {
		final Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("vote_count", voteCount);

		return new MapSqlParameterSource(param);
	}

	protected MapSqlParameterSource mapIdParameter(final String id) {
		final Map<String, Object> param = new HashMap<>();
		param.put("id", id);

		return new MapSqlParameterSource(param);

	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

}
