package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    public Rating insertRating(int id_content, int id_user, int rating);
    public List<ShowRatingByContentDTO> listContentOrderByScore();
    public List<Rating> listRatingByUser(int userId);
}
