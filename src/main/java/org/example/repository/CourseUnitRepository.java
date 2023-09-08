package org.example.repository;

import org.example.entity.CourseUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseUnitRepository extends JpaRepository<CourseUnit, Long> {
    @Query("SELECT cu.unitName, cu.unitSchedule, cu.unitSubject, cu.order, cu.descTitle1, cu.descContent1, cu.descTitle2, cu.descContent2, cu.videoUrl, cu.videoFormat, cu.creditUnits FROM CourseUnit cu WHERE cu.courseId = :courseId")
    List<Object[]> findCourseUnitDetailsByCourseId(@Param("courseId") int courseId);
    CourseUnit save(CourseUnit courseUnit);
    @Query("SELECT u.descTitle1, u.descContent1, u.descTitle2, u.descContent2 FROM Unit u WHERE u.unitId = ?1")
    List<Object[]> findDescAndContentByUnitId(Long unitId);
    List<CourseUnit> findAll();
    String findVideoUrlByUnitId(int unitId);
}
