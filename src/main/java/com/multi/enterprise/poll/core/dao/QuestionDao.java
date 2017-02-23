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

	private String SELECT_PAGINATED_QUESTION = "select * from question where question_index > %d order by question_index limit %d";

	private String SELECT_LATEST_PAGINATED_QUESTION = "SELECT * FROM question order by question_index desc limit %d";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.commons.jdbc.dao.DocumentAccess#getDocumentClass()
	 */
	public Class<Question> getDocumentClass() {
		return Question.class;
	}

	public QuestionList getPaginatedQuestion(final int questionIndex, final int limit) {
		final String query = String.format(SELECT_PAGINATED_QUESTION, questionIndex, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		final Comparator<Question> comparator = (q1, q2) -> Integer.compare(q1.getQuestionIndex(),
				q2.getQuestionIndex());
		final Question question = questions.stream().min(comparator).get();
		final QuestionList questionList = new QuestionList();

		questionList.setLastQuestionIndex(question.getQuestionIndex());
		questionList.setLimit(limit);

		if (limit > questions.size()) {
			questionList.setLastPage(true);
		}
		questionList.setQuestions(questions);

		return questionList;

	}

	public QuestionList getLatestPaginatedQuestion(final int limit) {
		final String query = String.format(SELECT_LATEST_PAGINATED_QUESTION, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		final Comparator<Question> comparator = (q1, q2) -> Integer.compare(q1.getQuestionIndex(),
				q2.getQuestionIndex());
		final Question question = questions.stream().min(comparator).get();
		final QuestionList questionList = new QuestionList();

		questionList.setLastQuestionIndex(question.getQuestionIndex());
		questionList.setLimit(limit);

		if (limit > questions.size()) {
			questionList.setLastPage(true);
		}
		questionList.setQuestions(questions);

		return questionList;

	}

}
