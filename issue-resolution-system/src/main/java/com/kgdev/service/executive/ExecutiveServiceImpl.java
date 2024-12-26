package com.kgdev.service.executive;

import com.kgdev.dao.ExecutiveStore;
import com.kgdev.enums.IssueType;
import com.kgdev.model.Executive;

public class ExecutiveServiceImpl implements ExecutiveService {
    private final ExecutiveStore executiveStore;
    public ExecutiveServiceImpl(ExecutiveStore executiveStore){
        this.executiveStore = executiveStore;
    }
    @Override
    public Executive add(String name, String employeeId, IssueType specialisation) {
        Executive executive =  Executive.create(name, employeeId, specialisation);
        executiveStore.create(executive);
        return executive;
    }

}
