/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.multi.enterprise.types.exception.ServiceException;

/**
 * @author Robot
 *
 */

@Service
public class EncryptionService {

	public String encrypt(String strToEncrypt, byte[] secret) throws ServiceException {
		try {
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, this.setKey(secret));
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new ServiceException("Exception occured while encrypting ", e);
		}
	}

	public String decrypt(String strToDecrypt, byte[] secret) throws ServiceException {
		try {
			final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, setKey(secret));
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			throw new ServiceException("Exception occured while decrypting ", e);
		}
	}

	private SecretKeySpec setKey(byte[] key) throws ServiceException {

		try {
			final MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			return new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Exception occured while generating secure key", e);
		}
	}

}
