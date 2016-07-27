/**
 * 
 */
package com.multi.enterprise.poll.core.config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.multi.enterprise.commons.config.BaseMainConfig;
import com.multi.enterprise.commons.config.PropertiesConfig;

/**
 * @author Robot
 *
 */

@Configuration
@ComponentScan(basePackages = { "com.multi.enterprise.poll.core", "com.multi.enterprise.core",
		"com.multi.enterprise.commons.config", "com.multi.enterprise.commons.bootstrap",
		"com.multi.enterprise.commons.jdbc" }, excludeFilters = { @Filter(Configuration.class),
		@Filter(Controller.class), @Filter(ControllerAdvice.class) })
public class MainConfig extends BaseMainConfig {

	@PropertySources({

	@PropertySource(value = "/Users/Rohit/properties/app.properties", ignoreResourceNotFound = true) })
	@Configuration
	public static class AppConfig extends PropertiesConfig {

	}

}
