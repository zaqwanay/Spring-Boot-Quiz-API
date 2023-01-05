package com.cooksys.quiz_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

	private final QuizService quizService;

	@GetMapping
	public List<QuizResponseDto> getAllQuizzes() {
		return quizService.getAllQuizzes();
	}

	// TODO: Implement the remaining 6 endpoints from the documentation.

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
		return quizService.createQuiz(quizRequestDto);

	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{id}")
	public QuizResponseDto deleteQuiz(@PathVariable Long id) throws NotFoundException {
		return quizService.deleteQuiz(id);
	}

	@PatchMapping("/{id}/rename/{newName}")
	public QuizResponseDto updateQuizName(@PathVariable Long id, @PathVariable String newName) throws NotFoundException {
		return quizService.updateQuizName(id, newName);
	}

	@GetMapping("/{id}/random")
	public QuestionResponseDto randomQuizQuestion(@PathVariable Long id) throws NotFoundException {
		return quizService.randomQuizQuestion(id);
	}

	@PatchMapping("/{id}/add")
	public QuizResponseDto addQuestionToQuiz(@PathVariable Long id,
			@RequestBody QuestionRequestDto questionRequestDto) throws NotFoundException {
		return quizService.addQuestionToQuiz(id, questionRequestDto);
	}

	@DeleteMapping("/{id}/delete/{questionID}")
	public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable Long id, @PathVariable Long questionID) throws NotFoundException {
		return quizService.deleteQuestionFromQuiz(id, questionID);
	}
}
