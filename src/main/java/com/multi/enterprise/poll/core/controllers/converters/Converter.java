/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

/**
 * @author Robot
 *
 */
public interface Converter<R, T> {

	public R internalize(final T entity);

	public T externalize(final R entity);

}
