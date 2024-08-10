package org.example.services.executive;

import org.example.enums.IssueType;


public interface ExecutiveService {
    boolean addExecutive(String name, String employeeId, IssueType specialisation);
}
