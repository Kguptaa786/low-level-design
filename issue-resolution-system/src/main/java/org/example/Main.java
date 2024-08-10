package org.example;

import org.example.dao.ExecutiveStore;
import org.example.dao.InMemoryExecutiveStore;
import org.example.dao.InMemoryIssueStore;
import org.example.dao.IssueStore;
import org.example.enums.IssueAssignmentStrategyEnum;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Issue;
import org.example.services.executive.ExecutiveService;
import org.example.services.executive.ExecutiveServiceImpl;
import org.example.services.issue.IssueService;
import org.example.services.issue.IssueServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        ExecutiveStore executiveStore = new InMemoryExecutiveStore();
        IssueStore issueStore = new InMemoryIssueStore();

        ExecutiveService executiveService = new ExecutiveServiceImpl(executiveStore);
        executiveService.addExecutive("Krishna", "1", IssueType.DELIVERY);
        executiveService.addExecutive("Nikhil", "2", IssueType.DELIVERY);
        executiveService.addExecutive("Ayush", "3", IssueType.DELIVERY);
        executiveService.addExecutive("Anand", "4", IssueType.PAYMENT);
        executiveService.addExecutive("Prakhar", "5", IssueType.PAYMENT);
        executiveService.addExecutive("John", "6", IssueType.RESTAURANT);


        IssueService issueService = new IssueServiceImpl(IssueAssignmentStrategyEnum.SEQUENTIAL, issueStore, executiveStore);

        issueService.createIssue("Order1","Failure in payment", IssueType.PAYMENT);
        issueService.createIssue("Order2", "Failure in restaurant", IssueType.RESTAURANT);
        issueService.createIssue("Order3","Failure in restaurant again", IssueType.RESTAURANT);
        issueService.createIssue("Order4", "Failure in delivery", IssueType.DELIVERY);
        issueService.createIssue("Order5", "Failure in delivery", IssueType.DELIVERY);
        issueService.createIssue("Order6", "Failure in delivery", IssueType.DELIVERY);
        issueService.createIssue("Order7", "Failure in delivery", IssueType.DELIVERY);
        issueService.createIssue("Order8","Failure in payment", IssueType.PAYMENT);

        System.out.println("Print all assigned issues");
        printIssueList(issueService.fetchIssues(IssueStatus.ASSIGNED, null));

        System.out.println("Print all submitted issues");
        printIssueList(issueService.fetchIssues(IssueStatus.SUBMITTED, null));

        issueService.updateIssue("Order1", IssueStatus.IN_PROGRESS);

        System.out.println("Print all in progress issues");
        printIssueList(issueService.fetchIssues(IssueStatus.IN_PROGRESS, null));
        issueService.updateIssue("Order1", IssueStatus.FIXED);


        System.out.println("Print all assigned issues");
        printIssueList(issueService.fetchIssues(IssueStatus.ASSIGNED, null));



    }

    private static void printIssueList(List<Issue> issueList){
        for (Issue issue: issueList){
            System.out.println("orderId : "+issue.getOrderId() +" - issueStatus : "+issue.getIssueStatus() + " - issueType : "+issue.getIssueType() + " - employeeId : "+issue.getEmployeeId());
        }
    }
}
