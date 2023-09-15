package org.example.controller;

import org.example.entity.Tip;
import org.example.service.sql.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tip")
public class TipController {

    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<List<Tip>> getTipByUnitId(@PathVariable Long unitId) {
        List<Tip> tip =tipService.getTipByUnitId(unitId);

        if (tip.isEmpty()) {
            System.out.println("沒有找到");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("有找到");
            return ResponseEntity.ok(tip);
        }
    }
    @PostMapping("/insert")
    public Tip insertTip(@RequestBody Tip tip) {

        return tipService.saveTip(tip);
    }
}

