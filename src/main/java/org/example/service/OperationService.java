package org.example.service;

import org.example.entity.Operation;
import org.example.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }


    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

}
