package org.example.service;

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

    public CourseUnit save(CourseUnit courseUnit) {
        return courseUnitRepository.save(courseUnit);
    }

    public List<CourseUnit> findAll() {
        return courseUnitRepository.findAll();
    }

}

