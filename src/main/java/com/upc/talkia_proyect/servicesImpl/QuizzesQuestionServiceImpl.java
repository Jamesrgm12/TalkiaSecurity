package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionsByQuizDTO;
import com.upc.talkia_proyect.entities.Question;
import com.upc.talkia_proyect.entities.QuizzesQuestion;
import com.upc.talkia_proyect.repositories.AnswerRepository;
import com.upc.talkia_proyect.repositories.QuizzesQuestionRepository;
import com.upc.talkia_proyect.repositories.UserRepository;
import com.upc.talkia_proyect.services.QuizzesQuestionService;
import com.upc.talkia_proyect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizzesQuestionServiceImpl implements QuizzesQuestionService {
    @Autowired
    private QuizzesQuestionRepository qqRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserService userService;


    List<Double> pointsByLevel = Arrays.asList(20.0, 12.0, 10.0);

    @Override
    @Transactional
    public String answerQuestion(int qqId, String userAnswer) {
        QuizzesQuestion qq = qqRepository.getQuizzesQuestionById(qqId);
        Question question = qq.getQuestion();
        Double pointsQuiz = qq.getQuiz().getTotalPoints();
        Double pointsUser = qq.getQuiz().getUser().getTotalPoints();

        if(qq.getAttempt()<2 && !qq.getIs_correct()){
            qq.setUserAnswer(userAnswer);
            qq.setAttempt(qq.getAttempt() + 1);

            if(answerRepository.getCorrectAnswerByQuestionId(question.getId()).getDescription().equals(userAnswer)) {
                double gainedPoints = 0.0;

                if (qq.getAttempt() == 1) {
                     gainedPoints = pointsByLevel.get(question.getLevel().getId()-1);


                }
                if(qq.getAttempt()== 2){
                     gainedPoints = (pointsByLevel.get(question.getLevel().getId()-1))/(2.0);

                }
                qq.setPointsEarned(gainedPoints);
                qq.setIs_correct(true);
                pointsQuiz += gainedPoints;
                qq.getQuiz().setTotalPoints(pointsQuiz);

                //pointsUser += gainedPoints;
                //qq.getQuiz().getUser().setTotalPoints(pointsUser);

                //Actualizar cuando se acabe el quiz
                //Verificar aumento de nivel

                if(qqId % 4 == 0){
                    pointsUser += qq.getQuiz().getTotalPoints();
                    qq.getQuiz().getUser().setTotalPoints(pointsUser);
                    userService.updateLevelUser(qq.getQuiz().getUser().getId());

                }


                return qq.getAttempt() == 1 ?
                        "Correct! You have earned " + gainedPoints + " points." :
                        "Now correct! You have earned " + gainedPoints + " points.";

            }

            return "Incorrect. "+ question.getFeedback();
        }

        return "Has llegado al límite de intentos permitidos";

    }

    @Override
    public Double getAveragePoints(int userId) {
        return qqRepository.getAveragePoints(userId);
    }

    @Override
    public Long getTotalQuizzesCompleted(int userId) {
        return qqRepository.getTotalQuizzesCompleted(userId);
    }

    @Override
    public Double getAverageCorrectAnswers(int userId) {
        Long totalQuestions = qqRepository.getTotalQuestions(userId);
        Long correctAnswers = qqRepository.getTotalCorrectAnswers(userId);

        if (totalQuestions == 0) {
            return 0.0;
        }
        return correctAnswers * 1.0 / totalQuestions;
    }

    @Override
    public Integer getSecondAttemptCorrectAnswers(int quizId) {
        return qqRepository.getSecondAttemptCorrectAnswers(quizId);
    }

    @Override
    public Integer getCorrectAnswersCount(int quizId) {
        return qqRepository.getCorrectAnswersCount(quizId);
    }

    @Override
    public Double getPercentageCorrectAnswers(int quizId) {
        double correctAnswers = 1.0 * qqRepository.getCorrectAnswersCount(quizId);

        return correctAnswers/4.0;
    }

    @Override
    public List<ShowQuestionsByQuizDTO> listQuestionsByQuizId(int quizId){
        return qqRepository.listQuestionsByQuizId(quizId);

    }
}
