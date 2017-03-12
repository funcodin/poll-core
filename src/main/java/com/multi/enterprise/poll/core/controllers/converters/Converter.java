/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import com.multi.enterprise.types.exception.ServiceException;

/**
 * @author Robot
 *
 */
public interface Converter<R, T> {

	public R internalize(final T entity) throws ServiceException;

	public T externalize(final R entity) throws ServiceException;

}
