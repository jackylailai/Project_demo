package org.example.repository;

import org.example.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz save(Quiz quiz);
    List<Quiz> findByUnitId(Integer unitId);
}

