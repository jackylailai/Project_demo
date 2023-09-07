package org.example.service.sql;

import org.example.entity.Attendance;
import org.example.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance createAttendance(Attendance attendance) {
        // 可以在这里添加保存 Attendance 实体的逻辑
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendances() {
        // 获取所有 Attendance 记录的逻辑
        return attendanceRepository.findAll();
    }

}

