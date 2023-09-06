package org.example.repository;

import org.example.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // 插入操作
    Course save(Course course);

    // 查询操作
    List<Course> findByCourseName(String courseName);
}
