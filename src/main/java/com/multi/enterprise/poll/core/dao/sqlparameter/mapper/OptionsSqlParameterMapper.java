/**
 * 
 */
package com.multi.enterprise.poll.core.dao.sqlparameter.mapper;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.OrderedMapSqlParameterSource;
import com.multi.enterprise.commons.jdbc.dao.mapper.BaseSqlParameterMapper;
import com.multi.enterprise.types.poll.Options;

/**
 * @author Robot
 *
 */

@Repository
public class OptionsSqlParameterMapper extends BaseSqlParameterMapper<Options> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getTableName()
	 */
	public String getTableName() {
		return "options";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.JdbcRecordSqlParameterMapper#getIdColumnName()
	 */
	public String getIdColumnName() {
		return "id";
	}

	@Override
	public OrderedMapSqlParameterSource mapInsertSqlParams(final Options options) {
		final OrderedMapSqlParameterSource orderedMapSqlParameterSource = new OrderedMapSqlParameterSource();

		orderedMapSqlParameterSource.addValue("id", options.getId());
		orderedMapSqlParameterSource.addValue("question_id", options.getQuestionId());
		orderedMapSqlParameterSource.addValue("option_value", options.getOptionValue());
		orderedMapSqlParameterSource.addValue("option_type", options.getOptionType());
		orderedMapSqlParameterSource.addValue("vote_count", options.getVoteCount());
		orderedMapSqlParameterSource.addValue("created_date", new Date());
		orderedMapSqlParameterSource.addValue("modified_date", new Date());

		return orderedMapSqlParameterSource;
	}

}
