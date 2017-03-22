/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.Comment;
import com.multi.enterprise.types.poll.CommentList;

/**
 * @author Robot
 *
 */

@Repository
public class CommentDao extends BaseJdbcRecordAccess<Comment> {

	private String GET_PAGINATED_COMMENTS_BY_QUESTION_ID = "select comment_index, question_id, user_id, comment, created_date, modified_date from user_comments where question_id = :question_id and comment_index < %d order by comment_index desc limit %d";

	private String GET_FIRST_PAGE_COMMENTS_BY_QUESTION_ID = "select comment_index, question_id, user_id, comment, created_date, modified_date from user_comments where question_id = :question_id order by comment_index desc limit %d";

	private String GET_PAGINATED_COMMENTS_BY_USERID = "select comment_index, question_id, user_id, comment, created_date, modified_date from user_comments where user_id = :user_id and comment_index < %d order by comment_index desc limit %d";

	private String GET_FIRST_PAGINATED_COMMENTS_BY_USERID = "select comment_index, question_id, user_id, comment, created_date, modified_date from user_comments where user_id = :user_id by comment_index desc limit %d";

	public CommentList getPaginatedCommentsByUserId(final String userId, final int commentIndex, final int limit) {

		List<Comment> comments = new ArrayList<>();

		if (commentIndex < 1) {
			final String query = String.format(GET_FIRST_PAGINATED_COMMENTS_BY_USERID, limit);
			comments = this.jdbcTempalte.query(query, this.mapParams("user_id", userId), this.rowMapper);
		} else {
			final String query = String.format(GET_PAGINATED_COMMENTS_BY_USERID, commentIndex, limit);
			comments = this.jdbcTempalte.query(query, this.mapParams("user_id", userId), this.rowMapper);
		}
		final Comparator<Comment> comparator = (c1, c2) -> Integer.compare(c1.getCommentIndex(), c2.getCommentIndex());
		final CommentList commentList = new CommentList();
		final Comment comment = comments.stream().min(comparator).get();

		if (limit > comments.size() || comment.getCommentIndex() == 1) {
			commentList.setLastPage(true);
		}
		commentList.setLastCommentIndex(comment.getCommentIndex());
		commentList.setComments(comments);
		commentList.setLimit(limit);
		return commentList;

	}

	public CommentList getPaginatedCommentsForQuestion(final String questionId, final int commentIndex, final int limit) {

		List<Comment> comments = new ArrayList<>();

		if (commentIndex < 1) {
			final String query = String.format(GET_FIRST_PAGE_COMMENTS_BY_QUESTION_ID, limit);
			comments = this.jdbcTempalte.query(query, this.mapParams("question_id", questionId), this.rowMapper);
		} else {
			final String query = String.format(GET_PAGINATED_COMMENTS_BY_QUESTION_ID, commentIndex, limit);
			comments = this.jdbcTempalte.query(query, this.mapParams("question_id", questionId), this.rowMapper);

		}

		final Comparator<Comment> comparator = (c1, c2) -> Integer.compare(c1.getCommentIndex(), c2.getCommentIndex());
		final CommentList commentList = new CommentList();
		final Comment comment = comments.stream().min(comparator).get();

		if (limit > comments.size() || comment.getCommentIndex() == 1) {
			commentList.setLastPage(true);
		}
		commentList.setLastCommentIndex(comment.getCommentIndex());
		commentList.setComments(comments);
		commentList.setLimit(limit);
		return commentList;
	}

	protected MapSqlParameterSource mapParams(final String columnName, final String columnValue) {
		final Map<String, Object> param = new HashMap<>();
		param.put(columnName, columnValue);
		return new MapSqlParameterSource(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */
	@Override
	public Class<Comment> getDocumentClass() {
		return Comment.class;
	}

}
