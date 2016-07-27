/**
 * 
 */
package com.multi.enterprise.poll.core.config.servlet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.multi.enterprise.commons.servlet.BaseWebMvcConfig;

/**
 * @author Robot
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.multi.enterprise.commons.controllers" })
public class WebMvcConfig extends BaseWebMvcConfig {

}
