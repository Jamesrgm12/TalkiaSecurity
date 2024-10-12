package com.upc.talkia_proyect.dtos.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountHistoriesByObjectDTO {
    private String varType;
    private Long count;
}
