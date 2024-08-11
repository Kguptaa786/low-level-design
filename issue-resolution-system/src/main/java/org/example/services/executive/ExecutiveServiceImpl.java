package org.example.services.executive;

import org.example.dao.ExecutiveStore;
import org.example.enums.IssueType;
import org.example.model.Executive;

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
