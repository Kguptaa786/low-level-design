package org.example;

import org.example.dao.ExecutiveStore;
import org.example.dao.InMemoryExecutiveStore;
import org.example.dao.InMemoryIssueStore;
import org.example.dao.IssueStore;
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
        executiveService.add("Krishna", "1", IssueType.DELIVERY);
        executiveService.add("Nikhil", "2", IssueType.DELIVERY);
        executiveService.add("Ayush", "3", IssueType.DELIVERY);
        executiveService.add("Anand", "4", IssueType.PAYMENT);
        executiveService.add("Prakhar", "5", IssueType.PAYMENT);
        executiveService.add("John", "6", IssueType.RESTAURANT);


        IssueService issueService = new IssueServiceImpl("SEQUENTIAL", issueStore, executiveStore);

        issueService.create("Order1","Failure in payment", IssueType.PAYMENT);
        issueService.create("Order2", "Failure in restaurant", IssueType.RESTAURANT);
        issueService.create("Order3","Failure in restaurant again", IssueType.RESTAURANT);
        issueService.create("Order4", "Failure in delivery", IssueType.DELIVERY);
        issueService.create("Order5", "Failure in delivery", IssueType.DELIVERY);
        issueService.create("Order6", "Failure in delivery", IssueType.DELIVERY);
        issueService.create("Order7", "Failure in delivery", IssueType.DELIVERY);
        issueService.create("Order8","Failure in payment", IssueType.PAYMENT);

        System.out.println("Print all assigned issues");
        printIssueList(issueService.fetch(IssueStatus.ASSIGNED, null, null, null));

        System.out.println("Print all submitted issues");
        printIssueList(issueService.fetch(IssueStatus.SUBMITTED, null, null, null));

        issueService.update("Order1", IssueStatus.IN_PROGRESS);

        System.out.println("Print all in progress issues");
        printIssueList(issueService.fetch(IssueStatus.IN_PROGRESS, null, null, null));

        issueService.update("Order1", IssueStatus.FIXED);


        System.out.println("Print all fixed issues");
        printIssueList(issueService.fetch(IssueStatus.FIXED, null, null, null));



    }

    private static void printIssueList(List<Issue> issueList){
        for (Issue issue: issueList){
            System.out.println("orderId : "+issue.getOrderId() +" - issueStatus : "+issue.getStatus() + " - issueType : "+issue.getType() + " - employeeId : "+issue.getEmployeeId());
        }
    }
}
