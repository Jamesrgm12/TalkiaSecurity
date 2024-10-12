package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionAdminDTO;
import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionUserDTO;
import com.upc.talkia_proyect.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionAdminDTO(a.id, a.description, a.isCorrect) from Answer a where a.question.id=:questionId")
    List<ShowAnswersByQuestionAdminDTO> listAnswerByQuestionAdmin(@Param("questionId") int questionId);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionUserDTO(a.id, a.description) from Answer a where a.question.id=:questionId")
    List<ShowAnswersByQuestionUserDTO> listAnswerByQuestionUser(@Param("questionId") int questionId);

    @Query("select a from Answer a where a.question.id=:questionId and a.isCorrect=true")
    Answer getCorrectAnswerByQuestionId(@Param("questionId") int questionId);
}
