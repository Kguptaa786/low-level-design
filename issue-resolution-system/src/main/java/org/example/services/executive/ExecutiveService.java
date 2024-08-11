package org.example.services.executive;

import org.example.enums.IssueType;
import org.example.model.Executive;


public interface ExecutiveService {
    Executive add(String name, String employeeId, IssueType specialisation);
}
