package org.example.service.sql;

import org.example.entity.Course;
import org.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findCoursesByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }
    public List<Object[]> searchCourses(String keyword) {
        return courseRepository.searchCourses(keyword);
    }
    public List<Course> getCoursesByType(int courseType) {
        return courseRepository.findByCourseType(courseType);
    }
}
