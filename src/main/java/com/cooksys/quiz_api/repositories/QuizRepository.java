package com.cooksys.quiz_api.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

//	ResponseEntity<QuizResponseDto> saveAllAndFlush(QuizRequestDto quizRequestDto);

  // TODO: Do you need any derived queries? If so add them here.
//	Optional<Quiz> findByIdAndDeletedFalse(Long id);
//    List<Quiz> findAllByDeletedFalse();


}
