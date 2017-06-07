/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.multi.enterprise.types.error.ResponseException;
import com.multi.enterprise.types.exception.IllegalArgumentServiceException;

/**
 * @author Robot
 *
 */

@ControllerAdvice
public class PollCoreExceptionHandler {

	@ExceptionHandler(IllegalArgumentServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseException handleIllegalArgumentServiceException(
			final IllegalArgumentServiceException illegalArgumentServiceException) {
		final ResponseException responseException = new ResponseException();
		responseException.setErrorMessage(illegalArgumentServiceException.getMessage());
		responseException.setStatusCode(illegalArgumentServiceException.getHttpStatus());
		return responseException;
	}

}
