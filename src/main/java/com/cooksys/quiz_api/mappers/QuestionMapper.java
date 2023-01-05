package com.cooksys.quiz_api.mappers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.entities.Question;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AnswerMapper.class })
public interface QuestionMapper {

  QuestionResponseDto entityToResponseDto(Question entity);

  List<QuestionResponseDto> entitiesToDtos(List<Question> questions);

Question questionDtoToEntity(QuestionRequestDto questionRequestDto);

}
