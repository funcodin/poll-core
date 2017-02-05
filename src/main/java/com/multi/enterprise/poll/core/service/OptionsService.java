/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.OptionsDao;
import com.multi.enterprise.types.exception.ServiceException;
import com.multi.enterprise.types.poll.Options;

/**
 * @author Robot
 *
 */

@Service
public class OptionsService extends BaseRecordService<Options> {

	@Autowired
	OptionsDao optionsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.service.RecordService#entityClass()
	 */
	public Class<Options> entityClass() {
		return Options.class;
	}

	@Override
	public Options create(final Options options) throws ServiceException {
		options.setId(UUID.randomUUID().toString());
		super.create(options);
		return options;
	}

	public void addVote(final String optionId) throws ServiceException {
		final int updatedVoteCount = this.getVoteCount(optionId) + 1;
		this.optionsDao.incrementVote(optionId, updatedVoteCount);

	}

	public int getVoteCount(final String optionId) throws ServiceException {
		return this.optionsDao.getVoteCount(optionId);
	}

}
