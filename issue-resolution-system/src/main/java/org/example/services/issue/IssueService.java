package org.example.services.issue;


import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Issue;

import java.util.List;

public interface IssueService {
    boolean createIssue(String orderId, String description, IssueType issueType);
    List<Issue> fetchIssues(IssueStatus issueStatus, IssueType issueType);
    boolean updateIssue(String orderId, IssueStatus issueStatus);
    boolean resolveIssue(String orderId);

}
