package com.upc.talkia_proyect.dtos;

import com.upc.talkia_proyect.entities.Level;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class QuestionDTO {
    private Integer id;
    private String description;
    private String feedback;
    private LocalDateTime iCreatedAt;
    private String iCreatedBy;
    private LocalDateTime iModifiedAt;
    private String iModifiedBy;
    private Level level;
}
