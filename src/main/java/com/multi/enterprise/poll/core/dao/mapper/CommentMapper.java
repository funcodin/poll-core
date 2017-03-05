/**
 * 
 */
package com.multi.enterprise.poll.core.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.mapper.BaseRowMapper;
import com.multi.enterprise.types.poll.Comment;

/**
 * @author Robot
 *
 */
@Repository
public class CommentMapper extends BaseRowMapper<Comment> {

	@Override
	public Comment mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
		final Comment comment = new Comment();
		comment.setCommentIndex(resultSet.getInt("comment_index"));
		comment.setQuestionId(resultSet.getString("question_id"));
		comment.setUserId(resultSet.getString("user_id"));
		comment.setComment(resultSet.getString("comment"));
		comment.setCreatedDate(resultSet.getDate("created_date"));
		comment.setModifiedDate(resultSet.getDate("modified_date"));
		return comment;
	}
}
