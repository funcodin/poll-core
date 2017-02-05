/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.BaseCrudController;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;

/**
 * @author Robot
 *
 */

@RestController
@RequestMapping(value = PollCoreRestEndpoints.QUESTION)
public class PollQuestionsController extends BaseCrudController<Question> {

}
