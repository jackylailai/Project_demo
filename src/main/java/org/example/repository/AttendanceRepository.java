package org.example.repository;

import org.example.entity.Attendance;
import org.example.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance save(Attendance attendance);
    @Query("SELECT a.score, a.date FROM Attendance a WHERE a.username = :username")
    List<Object[]> getScoreDetailsByUsername(@Param("username") String username);

    List<Attendance> findByUsername(String username);
}


