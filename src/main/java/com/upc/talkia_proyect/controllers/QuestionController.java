package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.QuestionDTO;
import com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO;
import com.upc.talkia_proyect.entities.Question;
import com.upc.talkia_proyect.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/question")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO questionDTO){
        try {
            Question question = modelMapper.map(questionDTO, Question.class);
            question = questionService.updateQuestion(question);
            questionDTO = modelMapper.map(question, QuestionDTO.class);
            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/question")
    public ResponseEntity<?> insertQuestion(@RequestBody QuestionDTO questionDTO){
        try {
            Question question = modelMapper.map(questionDTO, Question.class);
            question = questionService.insertQuestion(question);
            questionDTO = modelMapper.map(question, QuestionDTO.class);
            return new ResponseEntity<>(questionDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/questions")
    public ResponseEntity<?> listQuestions(){
        try {
            List<Question>list = questionService.listQuestions();
            List<QuestionDTO> listDTO = modelMapper.map(list, List.class);
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/level/{level}")
    public ResponseEntity<?> listQuestionsByLevel(@PathVariable String level){
        try {
            List<ShowQuestionByLevelDTO> questionDTOs = questionService.listQuestionsByLevel(level);
            return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/question/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int id){
        try {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>("Pregunta eliminada correctamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
