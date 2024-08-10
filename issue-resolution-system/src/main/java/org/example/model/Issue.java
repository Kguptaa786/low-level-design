package org.example.model;

import lombok.Data;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;

import java.util.UUID;
@Data
public class Issue {
    private UUID id;
    private String orderId;
    private String description;
    private IssueType issueType;
    private IssueStatus issueStatus;
    private String employeeId;

    public Issue(String orderId, String description, IssueType issueType) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.description = description;
        this.issueType = issueType;
        this.issueStatus = IssueStatus.SUBMITTED;
        this.employeeId = null;
    }
}
