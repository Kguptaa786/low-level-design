package com.kgdev.service.executive;

import com.kgdev.enums.IssueType;
import com.kgdev.model.Executive;


public interface ExecutiveService {
    Executive add(String name, String employeeId, IssueType specialisation);
}
