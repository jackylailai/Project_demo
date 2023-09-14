package org.example.controller;

import org.example.entity.Tip;
import org.example.entity.Unit;
import org.example.service.sql.TipService;
import org.example.service.sql.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{unitId}")
    public Unit getUnitsByCourseId(@PathVariable Long unitId) {
        return unitService.getUnitsByUnitId(unitId);
    }
    @GetMapping("/videos-url/{unitId}")
    public ResponseEntity<String> getVideoUrlByUnitId(@PathVariable Long unitId) {

        String videoUrl = unitService.getVideoUrlByUnitId(unitId);

        if (videoUrl != null) {
            return ResponseEntity.ok(videoUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/tip-title/{unitId}")
    public ResponseEntity<List<String>> getTipTitlesByUnitId(@PathVariable Long unitId) {
        List<String> tipTitles = tipService.getTipTitlesByUnitId(unitId);

        if (!tipTitles.isEmpty()) {
            return ResponseEntity.ok(tipTitles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/insert")
    public Unit insertUnit(@RequestBody Unit unit) {
        return unitService.saveUnit(unit);
    }
}
