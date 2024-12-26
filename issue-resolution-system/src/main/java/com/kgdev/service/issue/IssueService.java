package com.kgdev.service.issue;


import com.kgdev.enums.IssueStatus;
import com.kgdev.enums.IssueType;
import com.kgdev.model.Issue;

import java.util.List;

public interface IssueService {
    Issue create(String orderId, String description, IssueType issueType);
    List<Issue> fetch(IssueStatus issueStatus, IssueType issueType, Integer pageNumber, Integer pageLimit);
    void update(String orderId, IssueStatus newStatus);
    void resolve(String orderId);

}
