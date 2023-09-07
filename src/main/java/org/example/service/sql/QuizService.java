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


    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // 可以实现其他业务逻辑方法
}
