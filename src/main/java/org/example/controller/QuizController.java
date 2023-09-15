package org.example.controller;

import javassist.NotFoundException;
import org.example.entity.Quiz;
import org.example.service.sql.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        if (quizzes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(quizzes);
    }


    @GetMapping("/{unitId}")
    public ResponseEntity<List<Quiz>> getQuizzesByUnitId(@PathVariable Long unitId) {
        List<Quiz> quiz = quizService.getQuizzesByUnitId(unitId);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quiz);
    }
//    @GetMapping("/title/{unitId}")
//    public String getQuizTitle(@PathVariable Long unitId) throws NotFoundException {
//        String title = quizService.getQuizTitleByUnitId(unitId);
//        if (title != null) {
//            return title;
//        } else {
//            throw new NotFoundException("Quiz title not found for unitId: " + unitId);
//        }
//    }
    @PostMapping("/insert")
    public Quiz insertQuiz(@RequestBody Quiz quiz) {

        return quizService.saveQuiz(quiz);
    }
}
