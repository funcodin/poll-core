/**
 * 
 */
package com.multi.enterprise.poll.core.controllers.converters;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.multi.enterprise.types.poll.OptionDTO;
import com.multi.enterprise.types.poll.Options;
import com.multi.enterprise.types.poll.Question;

/**
 * @author Robot
 *
 */
@Component
public class OptionsConverter implements Converter<Options, OptionDTO> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#internalize(java.lang.Object)
	 */
	@Override
	public Options internalize(final OptionDTO optionDto) {
		final Options options = new Options();
		options.setId(StringUtils.isEmpty(optionDto.getOptionId()) ? UUID.randomUUID().toString() : optionDto
				.getOptionId());
		options.setOptionValue(optionDto.getOptionValue());
		options.setVoteCount(optionDto.getVoteCount());
		return options;
	}

	public Options internalize(final OptionDTO optionDto, final Question question) {
		final Options options = this.internalize(optionDto);
		options.setQuestionId(question.getId());
		options.setOptionType(question.getOptionType().toUpperCase());
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.multi.enterprise.poll.core.controllers.converters.Converter#externalize(java.lang.Object)
	 */
	@Override
	public OptionDTO externalize(final Options options) {
		final OptionDTO optionDto = new OptionDTO();
		optionDto.setOptionId(options.getId());
		optionDto.setOptionValue(options.getOptionValue());
		optionDto.setVoteCount(options.getVoteCount());
		return optionDto;
	}

}
