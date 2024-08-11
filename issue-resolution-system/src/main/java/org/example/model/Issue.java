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
    private IssueType type;
    private IssueStatus status;
    private String employeeId;

    private Issue(String orderId, String description, IssueType type,IssueStatus status, String employeeId) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.description = description;
        this.type = type;
        this.status = status;
        this.employeeId = employeeId;
    }

    public static Issue create(String orderId, String description, IssueType issueType) {
        return new Issue(orderId, description, issueType, IssueStatus.ASSIGNED, null);
    }

}
