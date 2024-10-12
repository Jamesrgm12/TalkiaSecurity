package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionsByQuizDTO;
import com.upc.talkia_proyect.services.QuizzesQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizzesQuestionController {

    @Autowired
    private QuizzesQuestionService qqService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/getAveragePointsByUser/{userId}")
    public ResponseEntity<?> getAveragePoints(@PathVariable int userId) {
        try {
            Double averagePoints = qqService.getAveragePoints(userId);
            return new ResponseEntity<>(averagePoints, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/listTotalQuizzesCompleted/{userId}")
    public ResponseEntity<?> getTotalQuizzesCompleted(@PathVariable int userId) {
        try {
            Long totalQuizzesCompleted = qqService.getTotalQuizzesCompleted(userId);
            return new ResponseEntity<>(totalQuizzesCompleted, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/getAvgCorrectAnswers/{userId}")
    public ResponseEntity<?> getAverageCorrectAnswers(@PathVariable int userId){
        try {
            Double averageCorrectAnswers = qqService.getAverageCorrectAnswers(userId);
            return new ResponseEntity<>(averageCorrectAnswers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/quizzesQuestion/answerQuestion/{qqId}/{userAnswer}")
    public ResponseEntity<?> answerQuestion(@PathVariable int qqId, @PathVariable String userAnswer){
        try {
            String answer = qqService.answerQuestion(qqId, userAnswer);
            return new ResponseEntity<>(answer, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/getSecondAttemptCorrect/{quizId}")
    public ResponseEntity<?> getSecondAttemptCorrectAnswers(@PathVariable int quizId){
        try {
            Integer secondAttemptCorrectAnswers = qqService.getSecondAttemptCorrectAnswers(quizId);
            return new ResponseEntity<>(secondAttemptCorrectAnswers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/getCorrectAnswersCount/{quizId}")
    public ResponseEntity<?> getCorrectAnswersCount(@PathVariable int quizId){
        try {
            Integer correctAnswersCount = qqService.getCorrectAnswersCount(quizId);
            return new ResponseEntity<>(correctAnswersCount, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("quizzesQuestion/getPercentageCorrectAnswers/{quizId}")
    public ResponseEntity<?> getPercentageCorrectAnswers(@PathVariable int quizId){
        try {
            Double percentageCorrectAnswers = qqService.getPercentageCorrectAnswers(quizId);
            return new ResponseEntity<>(percentageCorrectAnswers, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("quizzesQuestion/listQuestionsByQuiz/{quizId}")
    public ResponseEntity<?> listQuestionsByQuizId(@PathVariable int quizId){
        try {
            List<ShowQuestionsByQuizDTO> questionDTOs = qqService.listQuestionsByQuizId(quizId);
            return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
