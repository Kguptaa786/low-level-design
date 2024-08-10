package org.example.dao;

import org.example.enums.IssueType;
import org.example.model.Executive;

import java.util.List;
import java.util.Map;

public interface ExecutiveStore {
    Executive addExecutive(Executive executive);
    Map<IssueType, List<Executive>> getSpecialisationAndExecutivesMap();
}
