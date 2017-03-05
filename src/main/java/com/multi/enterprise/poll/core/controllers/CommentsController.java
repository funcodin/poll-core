/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.CommentConverter;
import com.multi.enterprise.poll.core.controllers.converters.CommentListConverter;
import com.multi.enterprise.poll.core.service.CommentService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Comment;
import com.multi.enterprise.types.poll.CommentDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */
@RestController
@RequestMapping(value = PollCoreRestEndpoints.COMMENT)
public class CommentsController implements CrudController<CommentDTO> {

	@Autowired
	CommentConverter commentConverter;

	@Autowired
	CommentListConverter commentListConverter;

	@Autowired
	CommentService commentService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public CommentDTO create(@RequestBody CommentDTO create) throws ServiceException {
		final Comment comment = this.commentConverter.internalize(create);
		final Comment createdComment = this.commentService.create(comment);
		return this.commentConverter.externalize(createdComment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public CommentDTO getById(String id) throws ServiceException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("unsupported");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public CommentDTO update(CommentDTO update) throws ServiceException {
		final Comment comment = this.commentConverter.internalize(update);
		final Comment updateComment = this.commentService.update(comment);
		return this.commentConverter.externalize(updateComment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) throws ServiceException {
		this.commentService.delete(id);
	}

}
