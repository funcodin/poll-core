/**
 * 
 */
package com.multi.enterprise.poll.core.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.multi.enterprise.commons.jdbc.config.JdbcConfig;
import com.multi.enterprise.poll.core.config.root.MainConfig;
import com.multi.enterprise.poll.core.config.servlet.WebMvcConfig;

/**
 * @author Robot
 *
 */
public class SpringWebMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses
	 * ()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { MainConfig.class };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses
	 * ()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class, JdbcConfig.class };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/ws" };
	}

	@Override
	public void onStartup(final ServletContext context) throws ServletException {
		context.setInitParameter("webAppRootKey", "poll.core.root");
		// TODO log configuration
		super.onStartup(context);
	}

}
