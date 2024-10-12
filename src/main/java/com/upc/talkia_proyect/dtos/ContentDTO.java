package com.upc.talkia_proyect.dtos;

import com.upc.talkia_proyect.entities.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer year;
    private String type;
    private String theme;
    private String link;
    private LocalDateTime iCreatedAt;
    private String iCreatedBy;
    private LocalDateTime iModifiedAt;
    private String iModifiedBy;
    private Set<Level> levels = new HashSet<>();
}
