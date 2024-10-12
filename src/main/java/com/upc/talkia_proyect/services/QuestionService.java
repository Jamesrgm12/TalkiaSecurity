package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO;
import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.entities.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public Question insertQuestion(Question question);
    List<Question> getQuestionsByLevel(Level level);
    public Question updateQuestion(Question question);
    public List<Question> listQuestions();
    List<ShowQuestionByLevelDTO> listQuestionsByLevel(String level);
    public void deleteQuestion(int questionId);

}
