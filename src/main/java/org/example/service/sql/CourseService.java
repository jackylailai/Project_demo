package org.example.service.sql;

import org.example.entity.Course;
import org.example.entity.Unit;
import org.example.repository.CourseRepository;
import org.example.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UnitRepository unitRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository,UnitRepository unitRepository) {
        this.courseRepository = courseRepository;
        this.unitRepository = unitRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> findCoursesByCourseName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }
    public List<Object[]> searchCourses(String courseName) {
        return courseRepository.searchCourses(courseName);
    }
    public List<Course> getCoursesByType(int courseType) {
        return courseRepository.findByCourseType(courseType);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Unit> getUnitsByCourseId(Long courseId) {
        return unitRepository.findByCourseId(courseId);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    public boolean deleteCourseByCourseId(Long courseId) {

        courseRepository.deleteCourseByCourseId(courseId);

        return !courseRepository.existsById(courseId);
    }
}
