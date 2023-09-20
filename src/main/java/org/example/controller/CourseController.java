package org.example.controller;

import org.example.entity.Course;
import org.example.entity.Unit;
import org.example.service.sql.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
    //for test
    @PostMapping("/post")
    public String handlePostRequest(@RequestBody String requestBody) {

        System.out.println("Received POST request with body: " + requestBody);
        return "Response from server: Test Request received successfully";
    }
    //包含關鍵字跟課程列表的api
    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> searchCourses(@RequestParam(value="course-name",required = false, defaultValue = "") String courseName) {
        List<Object[]> courses = courseService.searchCourses(courseName);
        return ResponseEntity.ok(courses);
    }
    //呼叫團體課程
    @GetMapping("/type")
    public ResponseEntity<List<Course>> getCoursesByType(@RequestParam(value="course-type",required = false, defaultValue = "2") int courseType) {
        List<Course> courses = courseService.getCoursesByType(courseType);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/name/{courseName}")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String courseName) {
        List<Course> courses = courseService.findCoursesByCourseName(courseName);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/{courseId}")
    public List<Unit> getUnitsByCourseId(@PathVariable Long courseId) {
        return courseService.getUnitsByCourseId(courseId);
    }
    @PostMapping("/insert")
    public Course insertCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourseByCourseId(@PathVariable Long courseId) {
        boolean deleted = courseService.deleteCourseByCourseId(courseId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

