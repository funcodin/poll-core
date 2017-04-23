/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

/**
 * @author Robot
 *
 */

@Service
public class TemporaryPasswordGeneratorService {
	private SecureRandom random = new SecureRandom();

	public String generatePassword() {
		return new BigInteger(130, random).toString(32).substring(0, 8);
	}
}
