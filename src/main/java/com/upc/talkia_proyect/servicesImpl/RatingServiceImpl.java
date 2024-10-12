package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Content;
import com.upc.talkia_proyect.entities.Rating;
import com.upc.talkia_proyect.entities.User;
import com.upc.talkia_proyect.repositories.ContentRepository;
import com.upc.talkia_proyect.repositories.RatingRepository;
import com.upc.talkia_proyect.repositories.UserRepository;
import com.upc.talkia_proyect.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Rating insertRating(int id_content, int id_user, int score){
        User user = userRepository.findById(id_user).get();
        Content content = contentRepository.findById(id_content).get();
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setContent(content);
        rating.setScore(score);
        return ratingRepository.save(rating);
    }

    @Override
    public List<ShowRatingByContentDTO> listContentOrderByScore(){
        return ratingRepository.listContentOrderByScore();
    }

    @Override
    public List<Rating> listRatingByUser(int userId){
        return ratingRepository.listRatingByUser(userId);
    }
}
