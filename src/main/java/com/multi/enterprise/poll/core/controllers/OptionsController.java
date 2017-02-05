/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.BaseCrudController;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */

@RestController
@RequestMapping(value = PollCoreRestEndpoints.POLL)
public class OptionsController extends BaseCrudController<Options> {

	@RequestMapping(value = "/addPoll/{optionId}", method = RequestMethod.GET)
	public void incrementVoteCount(@PathVariable final String optionId) {
		// get option, add vote and update options
	}

}
