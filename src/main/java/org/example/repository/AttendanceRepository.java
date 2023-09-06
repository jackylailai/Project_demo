package org.example.repository;

import org.example.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance save(Attendance attendance);

    List<Attendance> getAllAttendances();
}

