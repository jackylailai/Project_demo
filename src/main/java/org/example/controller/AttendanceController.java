package org.example.controller;

import org.example.entity.Attendance;
import org.example.service.sql.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance") // 修改RequestMapping注解的值
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/insert")
    public Attendance insertAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }
}