/**
 * 
 */
package com.multi.enterprise.poll.core.config.servlet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.multi.enterprise.commons.servlet.BaseWebMvcConfig;

/**
 * @author Robot
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.multi.enterprise.commons.controllers",
		"com.multi.enterprise.poll.core.controllers", "com.multi.enterprise.poll.core.controllers.error" })
public class WebMvcConfig extends BaseWebMvcConfig {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}
