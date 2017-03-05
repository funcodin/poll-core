/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.Comment;
import com.multi.enterprise.types.poll.CommentDTO;

/**
 * @author Robot
 *
 */
@Component
public class CommentConverter implements Converter<Comment, CommentDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public Comment internalize(CommentDTO entity) {
		final Comment comment = new Comment();
		comment.setId(StringUtils.isEmpty(entity.getId()) ? UUID.randomUUID().toString() : entity.getId());
		comment.setComment(entity.getComment());
		comment.setUserId(entity.getUserId());
		comment.setQuestionId(entity.getQuestionId());

		return comment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public CommentDTO externalize(final Comment entity) {
		final CommentDTO commentDto = new CommentDTO();
		commentDto.setComment(entity.getComment());
		commentDto.setCommentIndex(entity.getCommentIndex());
		commentDto.setId(entity.getId());
		commentDto.setQuestionId(entity.getQuestionId());
		commentDto.setUserId(entity.getUserId());
		return commentDto;
	}

}
