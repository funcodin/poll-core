/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.OptionsDao;
import com.multi.enterprise.poll.core.dao.PersonalDetailDao;
import com.multi.enterprise.poll.core.dao.QuestionDao;
import com.multi.enterprise.poll.core.dao.SecureUserDao;
import com.multi.enterprise.poll.core.dao.UserDao;
import com.multi.enterprise.poll.core.dao.UserDetailDao;
import com.multi.enterprise.types.exception.ClientException;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionList;
import com.multi.enterprise.types.poll.accounts.SecureUser;
import com.multi.enterprise.types.poll.accounts.User;

/**
 * @author Robot
 *
 */

@Service
public class UserService extends BaseRecordService<User> {

	@Autowired
	HashingService hashingService;

	@Autowired
	EncryptionService encryptionService;

	@Autowired
	SecureUserDao secureUserDao;

	@Autowired
	UserDao userDao;

	@Autowired
	PersonalDetailDao personalDetailDao;

	@Autowired
	UserDetailDao userDetailDao;

	@Autowired
	QuestionDao questionDao;

	@Autowired
	OptionsDao optionsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	@Override
	public Class<User> entityClass() {
		return User.class;
	}

	@Override
	public User create(final User user) throws ServiceException {
		final SecureUser secureUser = this.hashingService.createSecureUser(user);

		final String passwordHash = this.hashingService.getSecuredString(user.getPassword(), secureUser.getSalt());
		final String encryptedEmail = this.encryptionService.encrypt(user.getPersonalDetails().getEmailAddress(),
				secureUser.getSalt());
		user.getPersonalDetails().setEmailAddress(encryptedEmail);

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getContactNumber())) {
			final String encryptedContact = this.encryptionService.encrypt(
					user.getPersonalDetails().getContactNumber(), secureUser.getSalt());
			user.getPersonalDetails().setContactNumber(encryptedContact);
		}

		user.setPassword(passwordHash);
		this.userDao.create(user);
		this.secureUserDao.create(secureUser);
		this.userDetailDao.create(user.getUserDetails());
		this.personalDetailDao.create(user.getPersonalDetails());

		return user;
	}

	public User getUserById(final String userId) throws ServiceException {
		final User user = this.userDao.getByUserId(userId);
		final SecureUser secureUser = this.secureUserDao.getByUserId(user.getUserId());

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getEmailAddress())) {
			user.getPersonalDetails().setEmailAddress(
					this.encryptionService.decrypt(user.getPersonalDetails().getEmailAddress(), secureUser.getSalt()));

		}

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getContactNumber())) {
			user.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(user.getPersonalDetails().getContactNumber(), secureUser.getSalt()));
		}

		return user;
	}

	public User getByUserName(final String userName) throws ServiceException {
		final User user = this.userDao.getByUserName(userName);
		final SecureUser secureUser = this.secureUserDao.getByUserId(user.getUserId());

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getEmailAddress())) {
			user.getPersonalDetails().setEmailAddress(
					this.encryptionService.decrypt(user.getPersonalDetails().getEmailAddress(), secureUser.getSalt()));

		}

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getContactNumber())) {
			user.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(user.getPersonalDetails().getContactNumber(), secureUser.getSalt()));
		}

		return user;
	}

	public User login(final User user) throws ServiceException, ClientException {

		final User foundUser = this.userDao.getByUserName(user.getUserName());
		final SecureUser secureUser = this.secureUserDao.getByUserId(foundUser.getUserId());
		final String passwordHash = this.hashingService.getSecuredString(user.getPassword(), secureUser.getSalt());
		foundUser.setPassword(passwordHash);

		if (!StringUtils.equals(foundUser.getPassword(), passwordHash)) {
			throw new ClientException("Invalid credentials ");
		}

		if (StringUtils.isNotEmpty(foundUser.getPersonalDetails().getEmailAddress())) {
			foundUser.getPersonalDetails().setEmailAddress(
					this.encryptionService.decrypt(foundUser.getPersonalDetails().getEmailAddress(),
							secureUser.getSalt()));

		}

		if (StringUtils.isNotEmpty(foundUser.getPersonalDetails().getContactNumber())) {
			foundUser.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(foundUser.getPersonalDetails().getContactNumber(),
							secureUser.getSalt()));
		}

		return foundUser;
	}

	public QuestionList validate(final User user) throws ServiceException, ClientException {
		// find user by username
		final User foundUser = this.userDao.getByUserName(user.getUserName());
		// find secure user

		final SecureUser secureUser = this.secureUserDao.getByUserId(foundUser.getUserId());
		final String passwordHash = this.hashingService.getSecuredString(user.getPassword(), secureUser.getSalt());
		foundUser.setPassword(passwordHash);

		if (!StringUtils.equals(foundUser.getPassword(), passwordHash)) {
			throw new ClientException("Invalid credentials ");
		}

		final QuestionList questionList = this.questionDao.getLatestPaginatedQuestion(5);

		for (Question question : questionList.getQuestions()) {
			final List<Options> options = this.optionsDao.getAllOptionsByQuestionId(question.getId());
			question.setTotalVotes(options.stream().mapToInt(option -> option.getVoteCount()).sum());
			question.setOptions(options);
		}
		return questionList;
	}
}
