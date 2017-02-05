/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.Options;

/**
 * @author Robot
 *
 */

@Repository
public class OptionsMapper extends BaseRowMapper<Options> {

	@Override
	public Options mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final Options options = new Options();
		options.setId(resultSet.getString("id"));
		options.setQuestionId(resultSet.getString("question_id"));
		options.setOptionType(resultSet.getString("option_type"));
		options.setOptionValue(resultSet.getString("option_value"));
		options.setVoteCount(resultSet.getInt("vote_count"));
		options.setCreatedDate(resultSet.getDate("created_date"));
		options.setModifiedDate(resultSet.getDate("modified_date"));
		return options;

	}

}
