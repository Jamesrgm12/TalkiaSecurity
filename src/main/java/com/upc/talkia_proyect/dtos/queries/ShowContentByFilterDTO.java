package com.upc.talkia_proyect.dtos.queries;

import com.upc.talkia_proyect.entities.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShowContentByFilterDTO {
    private String title;
    private int year;

}
