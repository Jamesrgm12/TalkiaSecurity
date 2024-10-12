package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO(r.content.title, avg(r.score), r.content.year) from Rating r group by r.content.title, r.content.year order by avg(r.score) desc")
    public List<ShowRatingByContentDTO> listContentOrderByScore();

    @Query("select r from Rating r where r.user.id = :userId")
    public List<Rating> listRatingByUser(@Param("userId") int userId);
}
