/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.ContactUs;

/**
 * @author Robot
 *
 */
@Service
public class ContactUsService extends BaseRecordService<ContactUs> {

	@Autowired
	EmailService emailService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<ContactUs> entityClass() {
		return ContactUs.class;
	}

	@Override
	public ContactUs create(ContactUs entity) throws ServiceException {
		emailService.sendContactUsEmail(entity);
		return entity;
	}

}
