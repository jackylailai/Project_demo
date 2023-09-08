package org.example.controller;

import org.example.service.sql.CourseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseunits")
public class CourseUnitController {
    private final CourseUnitService courseUnitService;

    @Autowired
    public CourseUnitController(CourseUnitService courseUnitService) {
        this.courseUnitService = courseUnitService;
    }

    @GetMapping("/details")
    public ResponseEntity<List<Object[]>> getCourseUnitDetails(@RequestParam("courseId") int courseId) {
        List<Object[]> unitData = courseUnitService.getCourseUnitDetailsByCourseId(courseId);
        return ResponseEntity.ok(unitData);
    }
    @GetMapping("/{unitId}")
    public List<Object[]> getDescAndContentByUnitId(@PathVariable Long unitId) {
        return courseUnitService.getDescAndContentByUnitId(unitId);
    }
    @GetMapping("/videosUrl/{unitId}")
    public ResponseEntity<String> getVideoUrlByUnitId(@PathVariable int unitId) {

        String videoUrl = courseUnitService.getVideoUrlByUnitId(unitId);

        if (videoUrl != null) {
            return ResponseEntity.ok(videoUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
