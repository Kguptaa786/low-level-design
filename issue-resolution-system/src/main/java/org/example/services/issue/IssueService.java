package org.example.services.issue;


import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Issue;

import java.util.List;

public interface IssueService {
    Issue create(String orderId, String description, IssueType issueType);
    List<Issue> fetch(IssueStatus issueStatus, IssueType issueType, Integer pageNumber, Integer pageLimit);
    void update(String orderId, IssueStatus newStatus);
    void resolve(String orderId);

}
