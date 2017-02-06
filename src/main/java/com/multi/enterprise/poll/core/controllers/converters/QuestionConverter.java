/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.OptionDTO;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;
import com.multi.enterprise.types.poll.QuestionDTO;

/**
 * @author Robot
 *
 */

@Component
public class QuestionConverter implements Converter<Question, QuestionDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */

	@Autowired
	OptionsConverter optionsConverter;

	@Override
	public Question internalize(final QuestionDTO questionDto) {
		final Question question = new Question();
		final List<Options> optionList = new ArrayList<>();

		question.setId(StringUtils.isEmpty(questionDto.getQuestionId()) ? UUID.randomUUID().toString() : questionDto
				.getQuestionId());
		question.setQuestion(questionDto.getPollQuestion());
		question.setOptionType(questionDto.getOptionType());

		for (final OptionDTO optionDto : questionDto.getOptions()) {
			optionList.add(optionsConverter.internalize(optionDto, question));
		}
		question.setOptions(optionList);

		return question;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public QuestionDTO externalize(final Question question) {
		final QuestionDTO questionDto = new QuestionDTO();
		questionDto.setQuestionId(question.getId());
		questionDto.setPollQuestion(question.getQuestion());
		questionDto.setOptionType(question.getOptionType());
		questionDto.setTotalVotes(question.getTotalVotes());

		final List<OptionDTO> optionDtos = new ArrayList<>();
		for (final Options option : question.getOptions()) {
			optionDtos.add(this.optionsConverter.externalize(option));
		}

		questionDto.setOptions(optionDtos);

		questionDto.setUserId(question.getUserId());
		questionDto.setQrCodeUrl(question.getQrCodeUrl());
		questionDto.setMediaUrl(question.getMediaUrl());

		return questionDto;

	}

}
