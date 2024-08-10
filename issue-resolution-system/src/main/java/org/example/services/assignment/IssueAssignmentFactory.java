package org.example.services.assignment;

import org.example.dao.ExecutiveStore;
import org.example.dao.IssueStore;
import org.example.enums.IssueAssignmentStrategyEnum;

public class IssueAssignmentFactory {
    public static IssueAssignmentStrategy getIssueAssignmentStrategy(IssueAssignmentStrategyEnum type, ExecutiveStore executiveStore, IssueStore issueStore){
        switch (type) {
            case SEQUENTIAL:
                return new SequentialIssueAssignment(executiveStore, issueStore);
            default:
                throw new IllegalArgumentException("Unknown Issue Assignment Strategy type.");
        }
    }
}
