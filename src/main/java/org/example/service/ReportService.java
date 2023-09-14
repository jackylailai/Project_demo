package org.example.service;

import org.example.entity.Attendance;
import org.example.entity.Unit;
import org.example.entity.User;
import org.example.model.ReportItem;
import org.example.repository.AttendanceRepository;
import org.example.repository.UnitRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final UnitRepository unitRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportService(
            UnitRepository unitRepository,
            AttendanceRepository attendanceRepository,
            UserRepository userRepository
    ) {
        this.unitRepository = unitRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    public List<ReportItem> generateReport(String username) {
        List<ReportItem> report = new ArrayList<>();

        if (username == null || username.isEmpty()) {
//            如果沒有提供
            List<Attendance> allAttendance = attendanceRepository.findAll();

            for (Attendance attendance : allAttendance) {
                Unit unit = (Unit) unitRepository.findAllByUnitId(attendance.getUnitId());

                if (unit != null) {
                    User user = userRepository.findByUsername(attendance.getUsername());
// jpa username string id long
                    ReportItem reportItem = new ReportItem();
                    reportItem.setUsername(user.getUsername());
                    reportItem.setUnitName(unit.getName());
                    reportItem.setScore(attendance.getScore());

                    report.add(reportItem);
                }
            }
        } else {
//            如果有提供username
            List<Attendance> attendanceList = attendanceRepository.findByUsername(username);

            for (Attendance attendance : attendanceList) {
                Unit unit = (Unit) unitRepository.findAllByUnitId(attendance.getUnitId());

                if (unit != null) {
                    User user = userRepository.findByUsername(username);

                    ReportItem reportItem = new ReportItem();
                    reportItem.setUsername(user.getUsername());
                    reportItem.setUnitName(unit.getName());
                    reportItem.setScore(attendance.getScore());

                    report.add(reportItem);
                }
            }
        }

        return report;
    }


}

