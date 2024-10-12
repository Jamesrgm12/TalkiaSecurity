package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.RatingDTO;
import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Rating;
import com.upc.talkia_proyect.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rating/{id_content}/{id_user}/{score}")
    public ResponseEntity<?> insertRating(@PathVariable int id_content, @PathVariable int id_user,
                                       @PathVariable int score){
        try {
            Rating rating = ratingService.insertRating(id_content, id_user, score);
            RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
            return new ResponseEntity<>(ratingDTO, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/ratingsContentByScore")
    public ResponseEntity<?> listContentOrderByScore(){
        try {
            List<ShowRatingByContentDTO> ratingDTOs = ratingService.listContentOrderByScore();
            return new ResponseEntity<>(ratingDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ratingsByUser/{userId}")
    public ResponseEntity<?> listRatingByUser(@PathVariable int userId){
        try{
            List<Rating> ratings = ratingService.listRatingByUser(userId);
            List<RatingDTO> ratingsDTO = modelMapper.map(ratings,List.class);
            return new ResponseEntity<>(ratingsDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
