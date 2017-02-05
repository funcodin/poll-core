/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.Options;

/**
 * @author Robot
 *
 */

@Repository
public class OptionsDao extends BaseJdbcRecordAccess<Options> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.types.dao.RecordAccess#getDocumentClass()
	 */

	public Class<Options> getDocumentClass() {
		return Options.class;
	}

}
