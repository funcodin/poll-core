/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
public class QuestionDao extends BaseJdbcRecordAccess<Question> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.DocumentAccess#getDocumentClass()
	 */
	public Class<Question> getDocumentClass() {
		return Question.class;
	}

}
