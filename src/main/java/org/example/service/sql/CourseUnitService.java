package org.example.service.sql;

import org.example.entity.CourseUnit;
import org.example.repository.CourseUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseUnitService{
    private final CourseUnitRepository courseUnitRepository;

    @Autowired
    public CourseUnitService(CourseUnitRepository courseUnitRepository) {
        this.courseUnitRepository = courseUnitRepository;
    }
    public List<Object[]> getCourseUnitDetailsByCourseId(int courseId) {
        return courseUnitRepository.findCourseUnitDetailsByCourseId(courseId);
    }
    public List<Object[]> getDescAndContentByUnitId(Long unitId) {
        return courseUnitRepository.findDescAndContentByUnitId(unitId);
    }
    public CourseUnit save(CourseUnit courseUnit) {
        return courseUnitRepository.save(courseUnit);
    }

    public List<CourseUnit> findAll() {
        return courseUnitRepository.findAll();
    }
    public String getVideoUrlByUnitId(int unitId) {
        String videoUrl = courseUnitRepository.findVideoUrlByUnitId(unitId);
        return videoUrl;
    }
}

