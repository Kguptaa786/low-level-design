package com.kgdev.service.issue;

import com.kgdev.dao.ExecutiveStore;
import com.kgdev.dao.IssueStore;
import com.kgdev.dto.IssueFilterRequest;
import com.kgdev.enums.IssueStatus;
import com.kgdev.enums.IssueType;
import com.kgdev.model.Issue;
import com.kgdev.service.assignment.IssueAssignmentFactory;
import com.kgdev.util.PreValidationUtil;

import java.util.*;

public class IssueServiceImpl implements IssueService {
    private final String type;
    private final IssueStore issueStore;
    private final ExecutiveStore executiveStore;
    private static final Map<IssueStatus, Set<IssueStatus>> ALLOWED_STATUS_TRANSITION;
    public IssueServiceImpl(String type, IssueStore issueStore, ExecutiveStore executiveStore){
        this.type = type;
        this.issueStore = issueStore;
        this.executiveStore = executiveStore;
    }

    static {
        ALLOWED_STATUS_TRANSITION = Map.of(
                IssueStatus.SUBMITTED, Set.of(IssueStatus.ASSIGNED),
                IssueStatus.ASSIGNED, Set.of(IssueStatus.IN_PROGRESS, IssueStatus.INVALID),
                IssueStatus.IN_PROGRESS, Set.of(IssueStatus.FIXED, IssueStatus.INVALID));
    }
    @Override
    public Issue create(String orderId, String description, IssueType issueType) {
        if(issueStore.findByOrderId(orderId) != null) {
            throw new IllegalArgumentException("Issue is already submitted for order id : " + orderId + ". Please submit for new order id.");
        }
        Issue issue = Issue.create(orderId, description, issueType);
        PreValidationUtil.validateIssueRequest(issue);
        IssueAssignmentFactory.getIssueAssignmentStrategy(type, executiveStore, issueStore).assign(issue);
        issueStore.create(issue);
        return issue;
    }

    @Override
    public List<Issue> fetch(IssueStatus issueStatus, IssueType issueType, Integer pageNumber, Integer pageLimit) {
        IssueFilterRequest request = IssueFilterRequest.builder()
                .status(issueStatus)
                .type(issueType)
                .pageNumber(pageNumber == null ? 1 : pageNumber)
                .pageLimit(pageLimit == null ? 10 : pageLimit)
                .build();
        return issueStore.findByFilter(request);
    }

    @Override
    public void update(String orderId, IssueStatus newStatus) {
        Issue issue = issueStore.findByOrderId(orderId);
        if(issue == null){
            throw new IllegalArgumentException("No issue found for order id : "+orderId);
        }
        IssueStatus currentStatus = issue.getStatus();
        if (!ALLOWED_STATUS_TRANSITION.getOrDefault(currentStatus, Set.of()).contains(newStatus)) {
            throw new IllegalArgumentException("Issue cannot be updated. Requested status is not allowed for current status.");
        }
        issue.setStatus(newStatus);
        issueStore.update(issue);
    }

    @Override
    public void resolve(String orderId) {
        update(orderId, IssueStatus.FIXED);
    }
}
