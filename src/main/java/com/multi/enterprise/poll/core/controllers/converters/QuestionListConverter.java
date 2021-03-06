/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionDTO;
import com.multi.enterprise.types.poll.QuestionList;
import com.multi.enterprise.types.poll.QuestionListDTO;

/**
 * @author Robot
 *
 */

@Component
public class QuestionListConverter implements Converter<QuestionList, QuestionListDTO> {

	@Autowired
	QuestionConverter questionConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public QuestionList internalize(final QuestionListDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public QuestionListDTO externalize(final QuestionList questionList) {

		final QuestionListDTO questionListDto = new QuestionListDTO();
		final List<QuestionDTO> questionDtoList = new ArrayList<>();

		questionListDto.setLastPage(questionList.isLastPage());
		questionListDto.setLastQuestionIndex(questionList.getLastQuestionIndex());
		questionListDto.setLimit(questionList.getLimit());

		for (final Question question : questionList.getQuestions()) {
			questionDtoList.add(this.questionConverter.externalize(question));
		}

		questionListDto.setQuestions(questionDtoList);
		return questionListDto;
	}

}
