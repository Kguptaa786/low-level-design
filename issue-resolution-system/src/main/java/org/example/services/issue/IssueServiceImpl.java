package org.example.services.issue;

import org.example.dao.ExecutiveStore;
import org.example.dao.IssueStore;
import org.example.dto.IssueFilterRequest;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Issue;
import org.example.services.assignment.IssueAssignmentFactory;
import org.example.util.PreValidationUtil;

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
