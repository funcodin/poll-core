/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

	public Integer getVoteCount(final String optionId) {
		return this.jdbcTempalte.query(this.getVoteCountQuery(), this.mapIdParameter(optionId),
				new ResultSetExtractor<Integer>() {
					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						return rs.next() ? rs.getInt("vote_count") : 0;
					}
				});
	}

	public void incrementVote(final String optionId, final int voteCount) {
		this.jdbcTempalte.update(this.updateVoteCountQuery(), this.mapIdandVoteParameter(optionId, voteCount));
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
}
