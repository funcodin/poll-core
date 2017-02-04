/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Repository
public class QuestionMapper extends BaseRowMapper<Question> {

	@Override
	public Question mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final Question question = new Question();
		question.setId(resultSet.getString("id"));
		question.setUserId(resultSet.getString("userId"));
		question.setQuestion(resultSet.getString("question"));
		question.setOptionType(resultSet.getString("option_type"));
		question.setQrCodeUrl(resultSet.getString("qr_code_url"));
		question.setMediaUrl(resultSet.getString("media_url"));
		question.setTotalVotes(resultSet.getInt("total_votes"));
		question.setCreatedDate(resultSet.getDate("created_date"));
		question.setModifiedDate(resultSet.getDate("modified_date"));
		question.setOptions(new ArrayList<Options>());
		return question;
	}

}
