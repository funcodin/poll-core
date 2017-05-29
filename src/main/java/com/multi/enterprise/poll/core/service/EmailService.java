/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.ContactUs;

/**
 * @author Robot
 *
 */

@Service
public class EmailService {

	final static Session session;

	final static String from = "pollforfun@gmail.com";
	final static String password = "pole4fun";

	static {

		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
	}

	public void sendTempPasswordEmail(final String toEmailAddress, final String temporaryPassword)
			throws ServiceException {

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
			message.setSubject("Poll For Fun Password Reset");
			message.setText("Hello, below is your temporary password " + temporaryPassword);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new ServiceException(String.format("Error occured while sending out an email to %s", toEmailAddress));
		}

	}

	public void sendForgotUsernameEmail(final String toEmailAddress, final String userName) throws ServiceException {

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
			message.setSubject("Poll For Fun User name recovery");
			message.setText("Hello, below is your Poll for fun user name" + userName);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new ServiceException(String.format("Error occured while sending out an email to %s", toEmailAddress));
		}
	}

	public void sendForgotUsernamePasswordEmail(final String toEmailAddress, final String userName,
			final String tempPassword) throws ServiceException {

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
			message.setSubject("Poll For Fun User name and password recovery");
			message.setText(String.format("Hello, here is your Poll for fun user name %s and temporary password %s ",
					userName, tempPassword));

			Transport.send(message);

		} catch (MessagingException e) {
			throw new ServiceException(String.format("Error occured while sending out an email to %s", toEmailAddress));
		}

	}

	public void sendContactUsEmail(final ContactUs contactUs) throws ServiceException {

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));
			message.setSubject(String.format("You received a comment from %s ", contactUs.getName()));
			message.setText(String.format("Hello, User  %s with email %s says %s ", contactUs.getName(),
					contactUs.getEmail(), contactUs.getComment()));
			Transport.send(message);

		} catch (MessagingException e) {
			throw new ServiceException(String.format("Error occured while sending out an comment from  %s",
					contactUs.getEmail()));
		}

	}

}
