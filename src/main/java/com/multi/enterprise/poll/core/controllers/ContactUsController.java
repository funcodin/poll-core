/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.CrudController;
import com.multi.enterprise.poll.core.controllers.converters.ContactUsConverter;
import com.multi.enterprise.poll.core.service.ContactUsService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.ContactUs;
import com.multi.enterprise.types.poll.ContactUsDTO;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */
@RestController
@RequestMapping(value = PollCoreRestEndpoints.CONTACT_US)
public class ContactUsController implements CrudController<ContactUsDTO> {

	@Autowired
	ContactUsConverter contactUsConverter;

	@Autowired
	ContactUsService contactUsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#create(java.lang.Object)
	 */
	@Override
	public ContactUsDTO create(ContactUsDTO create) throws ServiceException {
		final ContactUs contactUs = this.contactUsConverter.internalize(create);
		this.contactUsService.create(contactUs);
		return create;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#getById(java.lang.String)
	 */
	@Override
	public ContactUsDTO getById(String id) throws ServiceException {
		throw new UnsupportedOperationException("UnSupported operation ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#update(java.lang.Object)
	 */
	@Override
	public ContactUsDTO update(ContactUsDTO update) throws ServiceException {
		throw new UnsupportedOperationException("UnSupported operation ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.controllers.CrudController#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) throws ServiceException {
		throw new UnsupportedOperationException("UnSupported operation ");

	}

}
