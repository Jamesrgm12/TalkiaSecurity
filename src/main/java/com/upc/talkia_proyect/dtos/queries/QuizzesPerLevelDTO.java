package com.upc.talkia_proyect.dtos.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizzesPerLevelDTO {
    private Long Quantity;
    private String levelName;
}