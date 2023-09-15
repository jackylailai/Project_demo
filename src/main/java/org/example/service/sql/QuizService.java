package org.example.service.sql;

import org.example.entity.Quiz;
import org.example.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getQuizzesByUnitId(Long unitId) {
        return quizRepository.findByUnitId(unitId);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

//    public String getQuizTitleByUnitId(Long unitId) {
//        List<Quiz> quiz = quizRepository.findByUnitId(unitId);
//
//        if (quiz != null) {
//            return quiz.toString();
//        } else {
//            return null;
//        }
//    }
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

}
