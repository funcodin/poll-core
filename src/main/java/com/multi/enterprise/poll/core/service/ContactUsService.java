/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.ContactUs;

/**
 * @author Robot
 *
 */
@Service
public class ContactUsService {

	@Autowired
	EmailService emailService;

	public ContactUs sendContactUs(ContactUs entity) throws ServiceException {
		emailService.sendContactUsEmail(entity);
		return entity;
	}

}
