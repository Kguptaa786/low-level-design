package org.example.services.issue;

import org.example.dao.ExecutiveStore;
import org.example.dao.InMemoryExecutiveStore;
import org.example.dao.InMemoryIssueStore;
import org.example.dao.IssueStore;
import org.example.enums.IssueAssignmentStrategyEnum;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Executive;
import org.example.model.Issue;
import org.example.services.assignment.IssueAssignmentFactory;
import org.example.util.PreValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IssueServiceImpl implements IssueService {
    private final IssueAssignmentStrategyEnum type;
    private final IssueStore issueStore;
    private final ExecutiveStore executiveStore;
    public IssueServiceImpl(IssueAssignmentStrategyEnum type, IssueStore issueStore, ExecutiveStore executiveStore){
        this.type = type;
        this.issueStore = issueStore;
        this.executiveStore = executiveStore;
    }
    @Override
    public boolean createIssue(String orderId, String description, IssueType issueType) {
        Issue issue = new Issue(orderId, description, issueType);
        PreValidationUtil.validateIssueRequest(issue);
        // check for duplicate issue request
        List<Issue> issueList = issueStore.getIssueList();
        for(Issue i: issueList){
            if(i.getOrderId() == orderId){
                throw new RuntimeException("Issue is already submitted for order id : "+orderId +". Please submit for new order id.");
            }
        }
        IssueAssignmentFactory.getIssueAssignmentStrategy(type, executiveStore, issueStore).assignIssue(issue);
        issueStore.addIssue(issue);
        return true;
    }

    @Override
    public List<Issue> fetchIssues(IssueStatus issueStatus, IssueType issueType) {
        List<Issue> fetchedIssue = new ArrayList<>();
        List<Issue> issueList = issueStore.getIssueList();
        if(issueStatus != null && issueType != null) {
            for (Issue issue: issueList){
                if(issue.getIssueStatus() == issueStatus && issue.getIssueType() == issueType){
                    fetchedIssue.add(issue);
                }
            }
        } else if(issueStatus != null) {
            for (Issue issue: issueList){
                if(issue.getIssueStatus() == issueStatus){
                    fetchedIssue.add(issue);
                }
            }
        } else if(issueType != null) {
            for (Issue issue: issueList){
                if(issue.getIssueType() == issueType){
                    fetchedIssue.add(issue);
                }
            }
        } else {
            fetchedIssue = issueList;
        }
        return fetchedIssue;
    }

    @Override
    public boolean updateIssue(String orderId, IssueStatus issueStatus) {
        Issue issue = getIssueFromOrderId(orderId);
        IssueStatus currentIssueStatus = issue.getIssueStatus();

        if(currentIssueStatus == IssueStatus.SUBMITTED && issueStatus == IssueStatus.ASSIGNED){
            issue.setIssueStatus(issueStatus);
        } else if(currentIssueStatus == IssueStatus.ASSIGNED && issueStatus == IssueStatus.IN_PROGRESS) {
            issue.setIssueStatus(issueStatus);
        } else if (currentIssueStatus == IssueStatus.IN_PROGRESS && issueStatus == IssueStatus.FIXED) {
            issue.setIssueStatus(issueStatus);
        } else if(currentIssueStatus == IssueStatus.ASSIGNED && issueStatus == IssueStatus.INVALID) {
            issue.setIssueStatus(issueStatus);
        } else if(currentIssueStatus == IssueStatus.IN_PROGRESS && issueStatus == IssueStatus.INVALID){
            issue.setIssueStatus(issueStatus);
        } else {
            throw new RuntimeException("Issue cannot be update. Requested status is not allowed for current status");
        }
        if(issueStatus == IssueStatus.FIXED || issueStatus == IssueStatus.INVALID){
            markAvailableToExecutive(issue);
            autoAssignQueueIssue();
        }
        return true;
    }

    @Override
    public boolean resolveIssue(String orderId) {
        Issue issue = getIssueFromOrderId(orderId);
        if(issue.getIssueStatus() != IssueStatus.IN_PROGRESS ) {
            throw new IllegalArgumentException("Issue cannot be marked to fixed.");
        }
        issue.setIssueStatus(IssueStatus.FIXED);
        markAvailableToExecutive(issue);
        autoAssignQueueIssue();
        return true;
    }

    private void markAvailableToExecutive(Issue issue){
        Map<IssueType, List<Executive>> specialisationAndExecutiveMap =  executiveStore.getSpecialisationAndExecutivesMap();
        List<Executive> executiveList = specialisationAndExecutiveMap.get(issue.getIssueType());
        for (Executive executive: executiveList){
            if(executive.getEmployeeId() == issue.getEmployeeId()){
                executive.setAvailable(true);
                return;
            }
        }
    }

    private void autoAssignQueueIssue(){
        List<Issue> issueList = issueStore.getIssueList();
        for(Issue issue: issueList){
            if(issue.getIssueStatus() == IssueStatus.SUBMITTED){
                IssueAssignmentFactory.getIssueAssignmentStrategy(type, executiveStore, issueStore).assignIssue(issue);
            }
        }
    }

    private Issue getIssueFromOrderId(String orderId){
        List<Issue> issueList = issueStore.getIssueList();
        for(Issue issue: issueList){
            if(issue.getOrderId() == orderId){
                return issue;
            }
        }
        throw new RuntimeException("No issue is present for order id : "+orderId);
    }
}
