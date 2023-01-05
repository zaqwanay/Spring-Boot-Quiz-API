package com.cooksys.quiz_api.mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;

@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface QuizMapper {


  List<QuizResponseDto> entitiesToDtos(List<Quiz> entities);

Quiz requestDtoToEntity(QuizRequestDto quizRequestDto);

QuizResponseDto entityToResponseDto(Quiz quiz);

//QuizResponseDto entityToResponseDto(Optional<Quiz> findById);

QuestionResponseDto entityToResponseDto(Question question);




}
