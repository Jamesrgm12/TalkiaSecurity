package com.upc.talkia_proyect.servicesImpl;


import com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO;
import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.entities.Question;
import com.upc.talkia_proyect.repositories.QuestionRepository;
import com.upc.talkia_proyect.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional
    public Question insertQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    @Transactional
    public Question updateQuestion(Question question) {
        if(questionRepository.existsById(question.getId())) {
            return questionRepository.save(question);
        }
        return null;
    }
    @Override
    @Transactional
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionsByLevel(Level level){
        return questionRepository.getQuestionsByLevel(level);
    }

    @Override
    public List<Question> listQuestions(){
        return questionRepository.findAll();
    }
    @Override
    public List<ShowQuestionByLevelDTO> listQuestionsByLevel(String level) {
        return questionRepository.listQuestionsByLevel(level);
    }

}
