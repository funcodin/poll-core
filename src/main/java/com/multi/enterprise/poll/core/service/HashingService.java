/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.accounts.SecureUser;
import com.multi.enterprise.types.poll.accounts.User;

/**
 * @author Robot
 *
 */

@Service
public class HashingService {

	public String getSecuredString(final String stringToHash, final byte[] salt) throws ServiceException {
		try {
			// Create MessageDigest instance for MD5
			final MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(salt);
			// Get the hash's bytes
			final byte[] bytes = md.digest(stringToHash.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Exception occured while generating secure password ", e);
		}
	}

	// Salt Generator
	private byte[] getSalt() throws ServiceException {
		try {
			final SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			final byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException nsae) {
			throw new ServiceException("Exception occured while generating secure password ", nsae);
		}
	}

	public SecureUser createSecureUser(final User user) throws ServiceException {
		final SecureUser secureUser = new SecureUser();
		secureUser.setId(UUID.randomUUID().toString());
		secureUser.setUserId(user.getUserId());
		secureUser.setSalt(this.getSalt());
		return secureUser;
	}

}
