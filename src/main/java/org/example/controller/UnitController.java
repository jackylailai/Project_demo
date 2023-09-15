package org.example.controller;

import org.example.entity.Course;
import org.example.entity.Tip;
import org.example.entity.Unit;
import org.example.service.sql.TipService;
import org.example.service.sql.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {
    private final UnitService unitService;
    private final TipService tipService;//用來處理
    @Autowired
    public UnitController(UnitService unitService,TipService tipService) {
        this.unitService = unitService;
        this.tipService = tipService;
    }

    @GetMapping("/details")
    public ResponseEntity<List<Object[]>> getUnitDetails(@RequestParam("course-id") Long courseId) {
        List<Object[]> unitData = unitService.getUnitDetailsByCourseId(courseId);
        return ResponseEntity.ok(unitData);
    }
    @GetMapping("/description/{unitId}")
    public List<Object[]> getDescAndContentByUnitId(@PathVariable Long unitId) {
        return unitService.getDescAndContentByUnitId(unitId);
    }
    @GetMapping
    public List<Unit> getAllunits() {
        return unitService.getAllunits();
    }
    @GetMapping("/{unitId}")
    public Unit getUnitsByCourseId(@PathVariable Long unitId) {
        return unitService.getUnitsByUnitId(unitId);
    }
    @GetMapping("/videos-url/{unitId}")
    public ResponseEntity<Unit> getVideoUrlByUnitId(@PathVariable Long unitId) {

        Unit videoUrl = unitService.getVideoUrlByUnitId(unitId);

        if (videoUrl != null) {
            return ResponseEntity.ok(videoUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @GetMapping("/tip-title/{unitId}")
//    public ResponseEntity<List<String>> getTipTitlesByUnitId(@PathVariable Long unitId) {
//        List<String> tipTitles = tipService.getTipTitlesByUnitId(unitId);
//
//        if (!tipTitles.isEmpty()) {
//            return ResponseEntity.ok(tipTitles);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PostMapping("/insert")
    public Unit insertUnit(@RequestBody Unit unit) {
        return unitService.saveUnit(unit);
    }
    @DeleteMapping("/{unitId}")
    public ResponseEntity<Void> deleteUnitById(@PathVariable Long unitId) {
        Optional<Unit> unit = unitService.getUnitById(unitId);
        if (unit.isPresent()) {
            unitService.deleteUnitByUnitId(unitId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnitById(@PathVariable Long id, @RequestBody Map<String, Object> unitUpdateRequest) {

        Unit updatedUnit = unitService.updateUnitById(id, unitUpdateRequest);

        if (updatedUnit != null) {
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
