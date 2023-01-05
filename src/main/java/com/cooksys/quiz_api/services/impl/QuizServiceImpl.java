package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.exceptions.BadRequestException;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	private final QuizMapper quizMapper;
	private final QuestionMapper questionMapper;
	private final QuestionRepository questionRepository;

//	Getting a single quiz (helper)
	private Quiz getQuiz(Long id) throws NotFoundException {
		Optional<Quiz> optionalQuiz = quizRepository.findById(id);
		if (optionalQuiz.isEmpty()) {
			throw new NotFoundException("quiz not found with id: " + id);
		}
		return optionalQuiz.get();
		
		}
	
	//Get question	
	private Question getQuestion(Long id, Long questionID) throws NotFoundException {
		Optional<Question> optionalQuestion = questionRepository.findById(questionID);

		 if (optionalQuestion.isEmpty()) {
		      throw new NotFoundException("Question not found with id: " + questionID);
		    }
		    return optionalQuestion.get();
		  }
	

	@Override
//	Getting all quizzes
	public List<QuizResponseDto> getAllQuizzes() {
		return quizMapper.entitiesToDtos(quizRepository.findAll());
	}

	@Override
//	Creating a quiz

	public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {

		Quiz quiz = quizMapper.requestDtoToEntity(quizRequestDto);

		for (Question question : quiz.getQuestions()) {
			question.setQuiz(quiz);

			for (Answer answer : question.getAnswers()) {
				answer.setQuestion(question);
			}
		}
		Quiz result = quizRepository.saveAndFlush(quiz);
		return quizMapper.entityToResponseDto(result);

	}

	@Override
//	calling the getQuiz() to get quiz to delete
	public QuizResponseDto deleteQuiz(Long id) throws NotFoundException {

		Quiz quizToDelete = getQuiz(id);
		quizRepository.delete(quizToDelete);
		
		return quizMapper.entityToResponseDto(quizToDelete);

	}

	@Override
	public QuizResponseDto updateQuizName(Long id, String newName) throws NotFoundException {

		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot rename quiz");
		} else if (newName == null) {
			throw new BadRequestException("must provide a name for quiz to rename quiz");
		}

		Quiz quizToRename = getQuiz(id);
		quizToRename.setName(newName);
		return quizMapper.entityToResponseDto(quizRepository.saveAndFlush(quizToRename));

	}

	@Override
	public QuestionResponseDto randomQuizQuestion(Long id) throws NotFoundException {
	Quiz result = getQuiz(id);
	Object[] questions = result.getQuestions().toArray();
		    Question rand = (Question) questions[new Random().nextInt(result.getQuestions().size())];
		   
		    return questionMapper.entityToResponseDto(rand);
	}

	@Override
	public QuizResponseDto addQuestionToQuiz(Long id, QuestionRequestDto questionRequestDto) throws NotFoundException {
	    Quiz quiz = getQuiz(id);

	    for (Question question : quiz.getQuestions()) {
	      question.setQuiz(quiz);
	      question.setText(questionRequestDto.getText());

	      for (Answer answer : question.getAnswers()) {
	        answer.setQuestion(question);
	      }
	    }
	    quiz.getQuestions().add(questionMapper.questionDtoToEntity(questionRequestDto));
	    return quizMapper.entityToResponseDto(quizRepository.saveAndFlush(quiz));
	  }
	

	@Override
	public QuestionResponseDto deleteQuestionFromQuiz(Long id, Long questionID) throws NotFoundException {
		 Question question = getQuestion(id, questionID);
		    questionRepository.delete(question);
		    return questionMapper.entityToResponseDto(question);
		  }



}
