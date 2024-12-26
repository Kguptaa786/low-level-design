package com.kgdev.dao;

import com.kgdev.dto.IssueFilterRequest;
import com.kgdev.model.Issue;

import java.util.List;

public interface IssueStore {
    Issue create(Issue issue);
    Issue findByOrderId(String orderId);
    boolean update(Issue issue);
    List<Issue> findByFilter(IssueFilterRequest request);
}
