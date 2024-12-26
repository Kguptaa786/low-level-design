package com.kgdev.service.assignment;

import com.kgdev.dao.ExecutiveStore;
import com.kgdev.dao.IssueStore;

public class IssueAssignmentFactory {
    public static IssueAssignmentStrategy getIssueAssignmentStrategy(String type, ExecutiveStore executiveStore, IssueStore issueStore){
        switch (type) {
            case "SEQUENTIAL":
                return new SequentialIssueAssignment(executiveStore, issueStore);
            default:
                throw new IllegalArgumentException("Unknown Issue Assignment Strategy type.");
        }
    }
}
