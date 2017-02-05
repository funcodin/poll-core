/**
 * 
 */
package com.multi.enterprise.poll.core.validation;

import java.util.Objects;

import org.springframework.util.StringUtils;

import com.multi.enterprise.types.exception.IllegalArgumentServiceException;
import com.multi.enterprise.types.poll.OptionType;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
public class QuestionValidation {

	public static void isValidQuestion(final Question question) throws IllegalArgumentServiceException {

		if (Objects.isNull(question)) {
			throw new IllegalArgumentServiceException("Questions object cannot be null ");
		}

		isValidOptionType(question.getOptionType());
		isValidInput("Question", question.getQuestion());

	}

	private static void isValidOptionType(final String optionType) throws IllegalArgumentServiceException {
		try {
			OptionType.valueOf(optionType.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new IllegalArgumentServiceException(String.format("Option Type is not valid %s ", optionType));

		}
	}

	private static void isValidInput(final String paramName, final String paramValue)
			throws IllegalArgumentServiceException {

		if (StringUtils.isEmpty(paramValue)) {
			throw new IllegalArgumentServiceException(String.format(" %s cannot be empty ", paramName));
		}
	}
}
