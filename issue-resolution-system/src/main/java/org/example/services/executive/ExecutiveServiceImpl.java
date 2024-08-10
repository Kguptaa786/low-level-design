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
    public boolean addExecutive(String name, String employeeId, IssueType specialisation) {
        Executive executive = new Executive(name, employeeId, specialisation);
        executiveStore.addExecutive(executive);
        return true;
    }

}
