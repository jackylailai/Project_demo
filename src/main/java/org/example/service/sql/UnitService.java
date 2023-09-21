package org.example.service.sql;

import org.example.entity.Unit;
import org.example.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UnitService{
    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }
    public List<Object[]> getUnitDetailsByCourseId(Long courseId) {
        return unitRepository.findUnitDetailsByCourseId(courseId);
    }
    public List<Object[]> getDescAndContentByUnitId(Long unitId) {
        return unitRepository.findDescAndContentByUnitId(unitId);
    }
    public Unit save(Unit Unit) {
        return unitRepository.save(Unit);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }
    public Unit getVideoUrlByUnitId(Long unitId) {
        Unit videoUrl = unitRepository.findVideoUrlByUnitId(unitId);
        return videoUrl;
    }

    public Unit getUnitsByUnitId(Long unitId) {
        Unit unit = unitRepository.findAllByUnitId(unitId);
        if (unit != null) {
            System.out.println("確實有找到unit"+unit);
        } else {
            System.out.println("沒有找到unit");
        }
        return unitRepository.findAllByUnitId(unitId);
    }

    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public List<Unit> getAllunits() {
        return unitRepository.findAll();
    }
    public void deleteUnitByUnitId(Long unitId) {
        unitRepository.deleteByUnitId(unitId);
    }

    public Optional<Unit> getUnitById(Long unitId) {return Optional.ofNullable(unitRepository.findAllByUnitId(unitId));
    }


    public Unit updateUnitById(Long id, Map<String, Object> unitUpdateRequest) {
        Optional<Unit> optionalUnit = unitRepository.findById(id);

        if (optionalUnit.isPresent()) {
            Unit existingUnit = optionalUnit.get();

            // 更新字段
            if (unitUpdateRequest.containsKey("unitName")) {
                existingUnit.setUnitName((String) unitUpdateRequest.get("unitName"));
                System.out.println("修改unitname");
            }

            if (unitUpdateRequest.containsKey("unitSchedule")) {
                existingUnit.setUnitSchedule((String) unitUpdateRequest.get("unitSchedule"));
            }
            if (unitUpdateRequest.containsKey("unitId")) {
                String unitIdStr = (String) unitUpdateRequest.get("unitId");
                long unitId = Long.parseLong(unitIdStr);
                existingUnit.setUnitId(unitId);
                System.out.println("修改unitId");
            }

            if (unitUpdateRequest.containsKey("videoUrl")) {
                existingUnit.setUnitSchedule((String) unitUpdateRequest.get("videoUrl"));
            }
            return unitRepository.save(existingUnit);
        } else {
            return null; // 返回 null 表示未找到记录
        }
    }
}

