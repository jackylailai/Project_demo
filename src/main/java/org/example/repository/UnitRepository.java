package org.example.repository;

import org.example.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query("SELECT u.unitName, u.unitSchedule, u.unitSubject, u.unitOrder, u.descTitle1, u.descContent1, u.descTitle2, u.descContent2, u.videoUrl, u.videoFormat, u.creditUnits FROM Unit u WHERE u.courseId = :courseId")
    List<Object[]> findUnitDetailsByCourseId(@Param("courseId") Long courseId);
    Unit save(Unit Unit);
    @Query("SELECT u.descTitle1, u.descContent1, u.descTitle2, u.descContent2 FROM Unit u WHERE u.unitId = ?1 ")
    List<Object[]> findDescAndContentByUnitId(Long unitId);
    List<Unit> findAll();
    String findVideoUrlByUnitId(Long unitId);

    Unit findAllByUnitId(Long unitId);
}
