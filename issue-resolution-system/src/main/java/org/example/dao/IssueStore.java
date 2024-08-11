package org.example.dao;

import org.example.dto.IssueFilterRequest;
import org.example.model.Issue;

import java.util.List;

public interface IssueStore {
    Issue create(Issue issue);
    Issue findByOrderId(String orderId);
    boolean update(Issue issue);
    List<Issue> findByFilter(IssueFilterRequest request);
}
