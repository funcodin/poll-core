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

	private String SELECT_PAGINATED_QUESTION = "select * from question where question_index < %d order by question_index desc limit %d";

	private String SELECT_LATEST_PAGINATED_QUESTION = "SELECT * FROM question order by question_index desc limit %d";

	private String SELECT_LASTEST_PAGINATED_QUESTION_FOR_USER = "select * from question where id not in"
			+ " ( select distinct(question_id) from user_poll where user_id = '%s' ) "
			+ "and userId <> '%s' order by question_index desc limit %d";

	private String SELECT_PAGINATED_QUESTION_FOR_USER = "select * from question where id not in"
			+ " ( select distinct(question_id) from user_poll where user_id = '%s' ) "
			+ "and userId <> '%s' and question_index < %d order by question_index desc limit %d";

	private String SELECT_PAGINATED_QUESTION_ASKED_BY_USER = "select * from question where userId = '%s' and question_index < %d order by question_index desc limit %d";

	private String SELECT_LATEST_PAGINATED_QUESTION_ASKED_BY_USER = "SELECT * FROM question where userId = '%s' order by question_index desc limit %d";

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
		return this.convertToQuestionList(questions, limit);

	}

	public QuestionList getLatestPaginatedQuestion(final int limit) {
		final String query = String.format(SELECT_LATEST_PAGINATED_QUESTION, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		return this.convertToQuestionList(questions, limit);

	}

	// Functionality to return questions not asked and voted by user

	public QuestionList getLatestPaginatedQuestionForUser(final String userId, final int limit) {
		final String query = String.format(SELECT_LASTEST_PAGINATED_QUESTION_FOR_USER, userId, userId, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		return this.convertToQuestionList(questions, limit);

	}

	public QuestionList getPaginatedQuestionForUser(final String userId, final int questionIndex, final int limit) {
		final String query = String.format(SELECT_PAGINATED_QUESTION_FOR_USER, userId, userId, questionIndex, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		return this.convertToQuestionList(questions, limit);

	}

	// Functionality to return paginated questions asked by user

	public QuestionList getLatestPaginatedQuestionAskedByUser(final String userId, final int limit) {
		final String query = String.format(SELECT_LATEST_PAGINATED_QUESTION_ASKED_BY_USER, userId, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		return this.convertToQuestionList(questions, limit);
	}

	public QuestionList getPaginatedQuestionAskedByUser(final String userId, final int questionIndex, final int limit) {
		final String query = String.format(SELECT_PAGINATED_QUESTION_ASKED_BY_USER, userId, questionIndex, limit);
		final List<Question> questions = this.jdbcTempalte.query(query, this.rowMapper);
		return this.convertToQuestionList(questions, limit);
	}

	private QuestionList convertToQuestionList(final List<Question> questions, final int limit) {
		final Comparator<Question> comparator = (q1, q2) -> Integer.compare(q1.getQuestionIndex(),
				q2.getQuestionIndex());
		final Question question = questions.stream().min(comparator).get();
		final QuestionList questionList = new QuestionList();

		questionList.setLastQuestionIndex(question.getQuestionIndex());
		questionList.setLimit(limit);

		if (limit > questions.size() || question.getQuestionIndex() == 1) {
			questionList.setLastPage(true);
		}
		questionList.setQuestions(questions);

		return questionList;
	}

}
