package org.example.controller;

import org.example.entity.Course;
import org.example.service.sql.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
    @GetMapping("/create")
    public String createCourse() {
        return "Empty form created.";
    }
    @PostMapping("/post")
    public String handlePostRequest(@RequestBody String requestBody) {

        System.out.println("Received POST request with body: " + requestBody);
        return "Response from server: Test Request received successfully";
    }
    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> searchCourses(@RequestParam(required = false, defaultValue = "") String keyword) {
        List<Object[]> courses = courseService.searchCourses(keyword);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/byType")
    public ResponseEntity<List<Course>> getCoursesByType(@RequestParam(required = false, defaultValue = "2") int courseType) {
        List<Course> courses = courseService.getCoursesByType(courseType);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/byName/{courseName}")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String courseName) {
        List<Course> courses = courseService.findCoursesByCourseName(courseName);
        return ResponseEntity.ok(courses);
    }
}

