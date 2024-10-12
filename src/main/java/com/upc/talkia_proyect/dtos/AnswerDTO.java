package com.upc.talkia_proyect.dtos;

import com.upc.talkia_proyect.entities.Question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Integer id;
    private String description;
    private Boolean isCorrect;
    private Question question;
}
