package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO;
import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByLevel(Level level);
    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO(q.description, q.iCreatedAt, q.iModifiedAt)from Question q where q.level.level=:level")
    List<ShowQuestionByLevelDTO> listQuestionsByLevel(String level);
}
