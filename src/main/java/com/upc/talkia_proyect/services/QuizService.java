package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.AveragePointsLevelDTO;
import com.upc.talkia_proyect.dtos.queries.QuizzesPerLevelDTO;
import com.upc.talkia_proyect.entities.Quiz;
import com.upc.talkia_proyect.entities.User;

import java.util.List;

public interface QuizService {
    public List<Quiz> listQuizzes();
    public Quiz insertQuiz(int userId);
    public List<Quiz> listQuizzesByUserId(int userId);
    public List<AveragePointsLevelDTO> listAveragePoints();
    public List<QuizzesPerLevelDTO> listQuizzesPerLevel();

}

