package org.example.controller;

import org.example.entity.Operation;
        import org.example.service.sql.OperationService;
        import org.springframework.beans.factory.annotation.Autowired;
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
}
