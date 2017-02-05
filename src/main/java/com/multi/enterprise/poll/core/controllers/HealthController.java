/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Robot
 *
 */
@RestController
// TODO use poll consts after deploy.
@RequestMapping(value = "/health")
public class HealthController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getApplicationStatus() {
		final StringBuilder builder = new StringBuilder("<PRE>");
		builder.append("Status: <FONT color=limegreen>RUNNING</FONT>");
		builder.append("</PRE>");
		return builder.toString();
	}

}
