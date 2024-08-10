package org.example.dao;

import org.example.model.Issue;

import java.util.List;

public interface IssueStore {
    Issue addIssue(Issue issue);
    List<Issue> getIssueList();
}
