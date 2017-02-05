/**
 * 
 */
package com.multi.enterprise.poll.core.service;

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

	public void addVote(final String optionId) throws ServiceException {
		final int updatedVoteCount = this.getVoteCount(optionId) + 1;
		this.optionsDao.incrementVote(optionId, updatedVoteCount);

	}

	public int getVoteCount(final String optionId) throws ServiceException {
		return this.optionsDao.getVoteCount(optionId);
	}

	/*
	 * We should not allow to create update or delete options directly. These options should always be performed from
	 * questions service.
	 */

	@Override
	public Options update(Options options) throws ServiceException {
		throw new ServiceException(String.format("Operation not permitted. Cannot delete options with id %s ",
				options.getId()));

	}

	@Override
	public void delete(String id) throws ServiceException {
		throw new ServiceException(String.format("Operation not permitted. Cannot delete options with id %s ", id));
	}

	@Override
	public Options create(final Options options) throws ServiceException {
		throw new ServiceException(String.format("Operation not permitted. Cannot delete options with id %s ",
				options.getId()));
	}

}
