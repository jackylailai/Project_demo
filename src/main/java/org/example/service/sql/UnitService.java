package org.example.service.sql;

import org.example.entity.Unit;
import org.example.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService{
    private final UnitRepository UnitRepository;

    @Autowired
    public UnitService(UnitRepository UnitRepository) {
        this.UnitRepository = UnitRepository;
    }
    public List<Object[]> getUnitDetailsByCourseId(Long courseId) {
        return UnitRepository.findUnitDetailsByCourseId(courseId);
    }
    public List<Object[]> getDescAndContentByUnitId(Long unitId) {
        return UnitRepository.findDescAndContentByUnitId(unitId);
    }
    public Unit save(Unit Unit) {
        return UnitRepository.save(Unit);
    }

    public List<Unit> findAll() {
        return UnitRepository.findAll();
    }
    public String getVideoUrlByUnitId(Long unitId) {
        String videoUrl = UnitRepository.findVideoUrlByUnitId(unitId);
        return videoUrl;
    }
}

