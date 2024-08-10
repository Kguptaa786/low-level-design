package org.example.dao;

import org.example.model.Issue;

import java.util.ArrayList;
import java.util.List;

public class InMemoryIssueStore implements IssueStore {
    private final List<Issue> issueList;
    public InMemoryIssueStore(){
        this.issueList = new ArrayList<>();
    }
    @Override
    public Issue addIssue(Issue issue) {
        issueList.add(issue);
        return issue;
    }
    @Override
    public List<Issue> getIssueList() {
        return issueList;
    }
}
