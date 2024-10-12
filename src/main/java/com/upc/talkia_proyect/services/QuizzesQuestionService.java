package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionsByQuizDTO;

import java.util.List;

public interface QuizzesQuestionService {

    public Double getAveragePoints(int userId);
    public Long getTotalQuizzesCompleted(int userId);
    public Double getAverageCorrectAnswers(int userId);
    public String answerQuestion(int qqId, String userAnswer);
    public Integer getSecondAttemptCorrectAnswers(int quizId);
    public Integer getCorrectAnswersCount(int quizId);
    public Double getPercentageCorrectAnswers(int quizId);
    public List<ShowQuestionsByQuizDTO> listQuestionsByQuizId(int quizId);

}
