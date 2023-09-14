package org.example.service.sql;

import org.example.entity.Attendance;
import org.example.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
// 在構造函數初始化後就不能做更動
    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository; //後面是參數傳入值 前面的屬性是上面宣告的
    }

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendances() {
        // 获取所有 Attendance 记录的逻辑
        return attendanceRepository.findAll();
    }

    public List<Object[]> getScoreDetailsByUsername(String username) {
        return getScoreDetailsByUsername(username);
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
}

