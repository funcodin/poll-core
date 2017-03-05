/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.CommentDao;
import com.multi.enterprise.types.poll.Comment;
import com.multi.enterprise.types.poll.CommentList;

/**
 * @author Robot
 *
 */
@Service
public class CommentService extends BaseRecordService<Comment> {

	@Autowired
	CommentDao commentDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<Comment> entityClass() {
		return Comment.class;
	}

	public CommentList getPaginatedCommentsForQuestion(final String questionId, final int lastIndex, final int limit) {
		return this.commentDao.getPaginatedCommentsForQuestion(questionId, lastIndex, limit);
	}

	public CommentList getPaginatedCommentsByUserId(final String userId, final int lastIndex, final int limit) {
		return this.commentDao.getPaginatedCommentsByUserId(userId, lastIndex, limit);
	}

}
