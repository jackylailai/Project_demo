package org.example.controller;

import org.example.entity.Operation;
import org.example.entity.Quiz;
import org.example.service.sql.OperationService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/insert")
    public Operation insertOperation(@RequestBody Operation operation) {

        return operationService.saveOperation(operation);
    }
    @GetMapping("/{unitId}")
    public ResponseEntity<List<Operation>> getOperationByUnitId(@PathVariable Long unitId) {
        List<Operation> operation = operationService.getOperationByUnitId(unitId);
        if (operation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(operation);
    }
}
