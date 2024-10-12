package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.AnswerDTO;
import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionAdminDTO;
import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionUserDTO;
import com.upc.talkia_proyect.entities.Answer;
import com.upc.talkia_proyect.services.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/answer/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable int id){
        try {
            answerService.deleteAnswer(id);
            return new ResponseEntity<>("Respuesta eliminada correctamente", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/answer")
    public ResponseEntity<?> insertAnswer(@RequestBody AnswerDTO answerDTO){
        try {
            Answer answer = modelMapper.map(answerDTO, Answer.class);
            answer = answerService.insertAnswer(answer);
            answerDTO = modelMapper.map(answer,AnswerDTO.class);
            return new ResponseEntity<>(answerDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/answers/listAnswersByQuestionAdmin/{questionId}")
    public ResponseEntity<?> listAnswerByQuestionAdmin(@PathVariable int questionId) {
        try {
            List<ShowAnswersByQuestionAdminDTO> answersDTOs= answerService.listAnswerByQuestionAdmin(questionId);
            return new ResponseEntity<>(answersDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/answers/listAnswersByQuestionUser/{questionId}")
    public ResponseEntity<?> listAnswerByQuestionUser(@PathVariable int questionId) {
        try {
            List<ShowAnswersByQuestionUserDTO> answersDTOs= answerService.listAnswerByQuestionUser(questionId);
            return new ResponseEntity<>(answersDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/answer")
    public ResponseEntity<?> updateAnswer(@RequestBody AnswerDTO answerDTO){
        try {
            Answer answer = modelMapper.map(answerDTO, Answer.class);
            answer = answerService.updateAnswer(answer);
            answerDTO = modelMapper.map(answer,AnswerDTO.class);
            return new ResponseEntity<>(answerDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
