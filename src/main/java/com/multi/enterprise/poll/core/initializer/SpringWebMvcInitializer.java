/**
 * 
 */
package com.multi.enterprise.poll.core.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

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

		return new Class<?>[] { MainConfig.class, JdbcConfig.class };
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
		return new Class<?>[] { WebMvcConfig.class };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/ws/*" };
	}

	@Override
	public void onStartup(final ServletContext context) throws ServletException {
		context.setInitParameter("webAppRootKey", "poll.core.root");
		context.setInitParameter("log4jConfigLocation", "classpath:/META-INF/properties/log4j_local.xml");
		Log4jConfigListener log4jListener = new Log4jConfigListener();
		context.addListener(log4jListener);
		super.onStartup(context);
	}

}
