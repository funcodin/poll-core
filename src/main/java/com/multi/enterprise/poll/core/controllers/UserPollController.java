/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.UserPollDTO;

/**
 * @author Robot
 *
 */

@RestController
public class UserPollController implements CrudController<UserPollDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	public UserPollDTO create(UserPollDTO create) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public UserPollDTO getById(String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public UserPollDTO update(UserPollDTO update) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws ServiceException {
		throw new ServiceException("Unsupported Method exception ");
	}

}
