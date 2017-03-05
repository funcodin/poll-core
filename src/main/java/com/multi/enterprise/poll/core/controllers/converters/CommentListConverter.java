/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.Comment;
import com.multi.enterprise.types.poll.CommentDTO;
import com.multi.enterprise.types.poll.CommentList;
import com.multi.enterprise.types.poll.CommentListDTO;

/**
 * @author Robot
 *
 */
@Component
public class CommentListConverter implements Converter<CommentList, CommentListDTO> {

	@Autowired
	CommentConverter commentConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public CommentList internalize(CommentListDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public CommentListDTO externalize(CommentList entity) {
		final CommentListDTO dto = new CommentListDTO();
		final List<CommentDTO> comments = new ArrayList<>();
		dto.setLastCommentIndex(entity.getLastCommentIndex());
		dto.setLastPage(entity.isLastPage());
		dto.setLimit(entity.getLimit());

		for (final Comment comment : entity.getComments()) {
			comments.add(this.commentConverter.externalize(comment));
		}
		dto.setComments(comments);
		return dto;
	}

}
