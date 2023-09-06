package org.example.repository;

import org.example.entity.CourseUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseUnitRepository extends JpaRepository<CourseUnit, Long> {
    CourseUnit save(CourseUnit courseUnit);

    List<CourseUnit> findAll();

}
