package org.example.dao;

import org.example.enums.IssueType;
import org.example.model.Executive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryExecutiveStore implements ExecutiveStore{
    private final Map<IssueType, List<Executive>> specialisationAndExecutiveMap;

    public InMemoryExecutiveStore() {
        this.specialisationAndExecutiveMap = new HashMap<>();
    }

    @Override
    public Executive addExecutive(Executive executive) {
        if(specialisationAndExecutiveMap.containsKey(executive.getSpecialisation())) {
            specialisationAndExecutiveMap.get(executive.getSpecialisation()).add(executive);
        } else {
            List<Executive>  specialisedExecutiveList = new ArrayList<>();
            specialisedExecutiveList.add(executive);
            specialisationAndExecutiveMap.put(executive.getSpecialisation(), specialisedExecutiveList);
        }
        return executive;
    }

    @Override
    public  Map<IssueType, List<Executive>> getSpecialisationAndExecutivesMap(){
        return specialisationAndExecutiveMap;
    }
}
