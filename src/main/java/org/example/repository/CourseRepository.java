package org.example.repository;

import org.example.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c.courseName, c.creditUnits FROM Course c WHERE c.courseName LIKE %:courseName%")
    List<Object[]> searchCourses(@Param("courseName") String keyword);
    List<Course> findByCourseType(int courseType);
    Course save(Course course);

    // 查询操作
    List<Course> findByCourseName(String courseName);
    void deleteCourseByCourseId(Long courseId);
}
