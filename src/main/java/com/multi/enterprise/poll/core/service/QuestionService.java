/**
 * 
 */
package com.multi.enterprise.poll.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.enterprise.commons.service.BaseRecordService;
import com.multi.enterprise.poll.core.dao.QuestionDao;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Service
public class QuestionService extends BaseRecordService<Question> {

	@Autowired
	QuestionDao qustionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.service.EntityService#entityClass()
	 */
	public Class<Question> entityClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
