package org.example.dao;

import org.example.dto.IssueFilterRequest;
import org.example.model.Issue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryIssueStore implements IssueStore {
    private final Map<String, Issue> issueMap;
    private final List<Issue> issueList;
    public InMemoryIssueStore(){
        this.issueMap = new HashMap<>();
        this.issueList = new ArrayList<>();
    }
    @Override
    public Issue create(Issue issue) {
        issueList.add(issue);
        issueMap.putIfAbsent(issue.getOrderId(), issue);
        return issue;
    }
    @Override
    public Issue findByOrderId(String orderId) {
        return issueMap.getOrDefault(orderId, null);
    }

    @Override
    public boolean update(Issue issue) {
        if(issueMap.containsKey(issue.getOrderId())){
            issueMap.put(issue.getOrderId(), issue);
            return true;
        }
        return false;
    }

    @Override
    public List<Issue> findByFilter(IssueFilterRequest request) {
        return issueList.stream()
                .filter(issue -> (request.getType() == null || issue.getType() == request.getType()))
                .filter(issue -> (request.getStatus() == null || issue.getStatus() == request.getStatus()))
                .skip((long) (request.getPageNumber() - 1) * request.getPageLimit())
                .limit(request.getPageLimit())
                .collect(Collectors.toList());
    }
}
