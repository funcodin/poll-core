/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.ContactUs;
import com.multi.enterprise.types.poll.ContactUsDTO;

/**
 * @author Robot
 *
 */
public class ContactUsConverter implements Converter<ContactUs, ContactUsDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public ContactUs internalize(final ContactUsDTO entity) throws ServiceException {
		final ContactUs contactUs = new ContactUs();
		contactUs.setComment(entity.getComment());
		contactUs.setEmail(entity.getEmail());
		contactUs.setName(entity.getName());
		return contactUs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public ContactUsDTO externalize(final ContactUs entity) throws ServiceException {
		final ContactUsDTO contactUsDTO = new ContactUsDTO();
		contactUsDTO.setComment(entity.getComment());
		contactUsDTO.setEmail(entity.getEmail());
		contactUsDTO.setName(entity.getName());
		return contactUsDTO;
	}

}
