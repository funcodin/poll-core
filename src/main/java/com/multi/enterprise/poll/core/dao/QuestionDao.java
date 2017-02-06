/**
 * 
 */
package com.multi.enterprise.poll.core.dao;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.multi.enterprise.commons.jdbc.dao.BaseJdbcRecordAccess;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionList;

/**
 * @author Robot
 *
 */
@Repository
public class QuestionDao extends BaseJdbcRecordAccess<Question> {

	private String SELECT_PAGINATED_QUESTION = "select * from question where id > %d order by id limit %d";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.DocumentAccess#getDocumentClass()
	 */
	public Class<Question> getDocumentClass() {
		return Question.class;
	}

	public QuestionList getPaginatedQuestion(final int questionIndex, final int limit) {

		final List<Question> questions = this.jdbcTempalte.query(SELECT_PAGINATED_QUESTION, this.rowMapper);
		// TODO update this to compare against index
		final Comparator<Question> comparator = (q1, q2) -> Integer.compare(q1.getTotalVotes(), q2.getTotalVotes());
		final Question question = questions.stream().max(comparator).get();
		final QuestionList questionList = new QuestionList();

		questionList.setLastQuestionIndex(question.getTotalVotes());
		questionList.setLimit(limit);

		if (limit > questions.size()) {
			questionList.setLastPage(true);
		}
		questionList.setQuestions(questions);

		return questionList;

	}

}
