package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.QuizDTO;
import com.upc.talkia_proyect.dtos.queries.AveragePointsLevelDTO;
import com.upc.talkia_proyect.dtos.queries.QuizzesPerLevelDTO;
import com.upc.talkia_proyect.entities.Quiz;
import com.upc.talkia_proyect.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizService quizService;

    private final ModelMapper modelMapper=new ModelMapper();

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quizzes")
    public ResponseEntity<?> listQuizzes(){
        try {
            List<Quiz> quizzes =quizService.listQuizzes();
            List<QuizDTO> quizDTOs= modelMapper.map(quizzes,List.class);
            return new ResponseEntity<>(quizDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/quiz/{userId}")
    public ResponseEntity<String> insertQuiz(@PathVariable int userId){
        try {
            quizService.insertQuiz(userId);
            return new ResponseEntity<>("Quiz asignado correctamente",HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quizzes/{userId}")
    public ResponseEntity<?> listQuizzesByUserId(@PathVariable int userId) {
        try {
            List<Quiz> quizzes = quizService.listQuizzesByUserId(userId);
            List<QuizDTO> quizzDTOs= modelMapper.map(quizzes,List.class);
            return new ResponseEntity<>(quizzDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quizzes/average")
    public ResponseEntity<?> listAveragePoints(){
        try {
            List<AveragePointsLevelDTO> quizzesDTOs = quizService.listAveragePoints();
            return new ResponseEntity<>(quizzesDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/quizzes/quantity")
    public ResponseEntity<?> listQuizzesPerLevel(){
        try {
            List<QuizzesPerLevelDTO> quizzesDTOs = quizService.listQuizzesPerLevel();
            return new ResponseEntity<>(quizzesDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
