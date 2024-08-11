package org.example.dao;

import org.example.enums.IssueType;
import org.example.model.Executive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryExecutiveStore implements ExecutiveStore{
    private final Map<String,Executive> executiveMap;
    private final Map<IssueType, List<Executive>> specialisationAndExecutiveMap;
    public InMemoryExecutiveStore() {
        executiveMap = new HashMap<>();
        this.specialisationAndExecutiveMap = new HashMap<>();
    }

    @Override
    public Executive create(Executive executive) {
        executiveMap.put(executive.getEmployeeId(), executive);
        specialisationAndExecutiveMap.computeIfAbsent(executive.getSpecialisation(), key -> new ArrayList<>())
                .add(executive);
        return executive;
    }

    @Override
    public List<Executive> findAllNotEqualSpecialisation(IssueType issueType) {
        return executiveMap.values().stream()
                .filter(executive -> !executive.getSpecialisation().equals(issueType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Executive executive) {
        if(executiveMap.containsKey(executive.getEmployeeId())){
            executiveMap.put(executive.getEmployeeId(), executive);
            return true;
        }
        return false;
    }

    @Override
    public List<Executive> findBySpecialisation(IssueType issueType) {
        return specialisationAndExecutiveMap.getOrDefault(issueType, new ArrayList<>());
    }
}
