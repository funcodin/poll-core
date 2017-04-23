/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.List;
import java.util.Objects;

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
import com.multi.enterprise.types.poll.accounts.UserDetails;
import com.multi.enterprise.types.poll.accounts.UserPersonalDetails;
import com.multi.enterprise.types.poll.accounts.UserReset;

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
	EmailService emailService;

	@Autowired
	TemporaryPasswordGeneratorService tempPasswordGenerator;

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

	@Autowired
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

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getContactNumber())) {
			user.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(user.getPersonalDetails().getContactNumber(), secureUser.getSalt()));
		}

		return user;
	}

	public User getByUserName(final String userName) throws ServiceException, ClientException {
		final User user = this.userDao.getByUserName(userName);
		final SecureUser secureUser = this.secureUserDao.getByUserId(user.getUserId());

		if (StringUtils.isNotEmpty(user.getPersonalDetails().getContactNumber())) {
			user.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(user.getPersonalDetails().getContactNumber(), secureUser.getSalt()));
		}

		return user;
	}

	public UserReset resetPasswordByEmail(final UserReset userReset) throws ServiceException, ClientException {
		final User user = this.userDao.getUserByEmail(userReset.getEmail());
		final String tempPassword = this.tempPasswordGenerator.generatePassword();
		user.setPassword(tempPassword);
		this.emailService.sendTempPasswordEmail(userReset.getEmail(), tempPassword);
		userReset.setResponseString(String.format(
				"Temporary password has been sent to %s . Please update the password once you login",
				userReset.getEmail()));
		this.update(user);
		userReset.setUserName(user.getUserName());

		return userReset;
	}

	public UserReset forgotUsername(final UserReset userReset) throws ServiceException, ClientException {
		final User user = this.userDao.getUserByEmail(userReset.getEmail());
		this.emailService.sendForgotUsernameEmail(userReset.getEmail(), user.getUserName());
		userReset.setUserName(user.getUserName());
		userReset.setResponseString(String.format("Username recovery email has be sent to %s ", userReset.getEmail()));
		return userReset;
	}

	public UserReset forgotUsernamePassword(final UserReset userReset) throws ServiceException, ClientException {
		final User user = this.userDao.getUserByEmail(userReset.getEmail());
		this.emailService.sendForgotUsernameEmail(userReset.getEmail(), user.getUserName());
		final String tempPassword = this.tempPasswordGenerator.generatePassword();
		user.setPassword(tempPassword);
		this.emailService.sendForgotUsernamePasswordEmail(userReset.getEmail(), user.getUserName(), tempPassword);

		userReset.setUserName(user.getUserName());
		userReset.setResponseString(String.format(
				"Username and Temporary password has been sent to %s . Please update the password once you login",
				userReset.getEmail()));

		return userReset;
	}

	public User login(final User user) throws ServiceException, ClientException {

		final User foundUser = this.userDao.getByUserName(user.getUserName());
		final SecureUser secureUser = this.secureUserDao.getByUserId(foundUser.getUserId());
		final String passwordHash = this.hashingService.getSecuredString(user.getPassword(), secureUser.getSalt());

		if (!StringUtils.equals(foundUser.getPassword(), passwordHash)) {
			throw new ClientException("Invalid credentials ");
		}

		if (StringUtils.isNotEmpty(foundUser.getPersonalDetails().getContactNumber())) {
			foundUser.getPersonalDetails().setContactNumber(
					this.encryptionService.decrypt(foundUser.getPersonalDetails().getContactNumber(),
							secureUser.getSalt()));
		}

		return foundUser;
	}

	public QuestionList validate(final User user) throws ServiceException, ClientException {
		final User foundUser = this.userDao.getByUserName(user.getUserName());
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

	@Override
	public User update(final User user) throws ServiceException {

		final User foundUser = this.userDao.getByUserId(user.getUserId());
		final SecureUser secureUser = this.secureUserDao.getByUserId(user.getUserId());
		if (Objects.isNull(foundUser)) {
			throw new ServiceException(" Unable to find user ");
		}

		foundUser.setPersonalDetails(this.updateUserPersonalDetails(user.getPersonalDetails(),
				foundUser.getPersonalDetails(), secureUser));

		foundUser.setUserDetails(this.updateUserDetails(user.getUserDetails(), foundUser.getUserDetails()));

		if (StringUtils.isNotEmpty(user.getPassword())) {
			foundUser.setPassword(this.hashingService.getSecuredString(user.getPassword(), secureUser.getSalt()));
		}

		this.userDao.update(foundUser);
		this.userDetailDao.update(foundUser.getUserDetails());
		this.personalDetailDao.update(foundUser.getPersonalDetails());

		return this.getUserById(foundUser.getUserId());
	}

	private UserDetails updateUserDetails(final UserDetails userDetails, final UserDetails foundUserDetails) {

		if (Objects.isNull(userDetails)) {
			return foundUserDetails;
		}

		if (Objects.isNull(foundUserDetails)) {
			return userDetails;
		}

		if (Objects.nonNull(userDetails.getAgeGroup())) {
			foundUserDetails.setAgeGroup(userDetails.getAgeGroup());
		}

		if (Objects.nonNull(userDetails.getGender())) {
			foundUserDetails.setGender(userDetails.getGender());
		}
		return foundUserDetails;
	}

	private UserPersonalDetails updateUserPersonalDetails(final UserPersonalDetails userPersonalDetails,
			final UserPersonalDetails foundUserPersonalDetails, final SecureUser secureUser) throws ServiceException {

		if (Objects.isNull(userPersonalDetails)) {
			return foundUserPersonalDetails;
		}

		if (Objects.isNull(foundUserPersonalDetails)) {
			return userPersonalDetails;
		}

		if (StringUtils.isNotEmpty(userPersonalDetails.getContactNumber())) {

			foundUserPersonalDetails.setContactNumber(this.encryptionService.encrypt(
					userPersonalDetails.getContactNumber(), secureUser.getSalt()));
		}

		if (StringUtils.isNotEmpty(userPersonalDetails.getFullName())) {
			foundUserPersonalDetails.setFullName(userPersonalDetails.getFullName());
		}

		if (StringUtils.isNotEmpty(userPersonalDetails.getEmailAddress())) {
			foundUserPersonalDetails.setEmailAddress(userPersonalDetails.getEmailAddress());
		}
		return foundUserPersonalDetails;

	}
}
