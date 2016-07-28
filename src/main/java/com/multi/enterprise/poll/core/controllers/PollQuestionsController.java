/**
 * 
 */
package com.multi.enterprise.poll.core.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.enterprise.commons.controllers.BaseCrudController;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */

@RestController
// TODO use poll consts after deploy.
@RequestMapping(value = "/question")
public class PollQuestionsController extends BaseCrudController<Question> {

}
