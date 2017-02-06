// /**
// *
// */
// package com.multi.enterprise.poll.core.controllers;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.multi.enterprise.commons.controllers.BaseCrudController;
// import com.multi.enterprise.poll.core.service.OptionsService;
// import com.multi.enterprise.types.exception.ServiceException;
// import com.multi.enterprise.types.poll.Options;
// import com.multi.enterprise.types.poll.consts.PollCoreRestEndpoints;
//
// /**
// * @author Robot
// *
// */
//
// @RestController
// @RequestMapping(value = PollCoreRestEndpoints.POLL)
// public class OptionsController extends BaseCrudController<Options> {
//
// @Autowired
// OptionsService optionsService;
//
// @RequestMapping(value = "/addPoll/{optionId}", method = RequestMethod.GET)
// public void incrementVoteCount(@PathVariable final String optionId) throws ServiceException {
// this.optionsService.addVote(optionId);
// }
//
// @RequestMapping(value = "/getPoll/{optionId}", method = RequestMethod.GET)
// public int getVoteCount(@PathVariable final String optionId) throws ServiceException {
// return this.optionsService.getVoteCount(optionId);
// }
//
// }
