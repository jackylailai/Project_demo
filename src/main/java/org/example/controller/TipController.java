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

    @GetMapping("/{tipId}")
    public ResponseEntity<Tip> getTipById(@PathVariable Long tipId) {
        List<Tip> tips = (List<Tip>) tipService.getTipByTipId(tipId);

        if (tips.isEmpty()) {
            return ResponseEntity.ok((Tip) tips);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/insert")
    public Tip insertTip(@RequestBody Tip tip) {

        return tipService.saveTip(tip);
    }
}

