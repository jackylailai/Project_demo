package org.example.web;

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
        return "Response from server: Request received successfully";
    }

    @GetMapping("/byName/{courseName}")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String courseName) {
        List<Course> courses = courseService.findCoursesByCourseName(courseName);
        return ResponseEntity.ok(courses);
    }
}

