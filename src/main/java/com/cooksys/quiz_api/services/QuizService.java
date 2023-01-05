package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;

import javassist.NotFoundException;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();

QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

QuizResponseDto deleteQuiz(Long id) throws NotFoundException;

QuizResponseDto updateQuizName(Long id, String newName) throws NotFoundException;

QuestionResponseDto randomQuizQuestion(Long id) throws NotFoundException;

QuizResponseDto addQuestionToQuiz(Long id, QuestionRequestDto questionRequestDto) throws NotFoundException;

QuestionResponseDto deleteQuestionFromQuiz(Long id, Long questionID) throws NotFoundException;






}
