package org.example.services.assignment;

import org.example.dao.ExecutiveStore;
import org.example.dao.InMemoryExecutiveStore;
import org.example.dao.InMemoryIssueStore;
import org.example.dao.IssueStore;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;
import org.example.model.Executive;
import org.example.model.Issue;

import java.util.List;
import java.util.Map;


public class SequentialIssueAssignment implements IssueAssignmentStrategy {

    private final ExecutiveStore executiveStore;
    private final IssueStore issueStore;
    public SequentialIssueAssignment(ExecutiveStore executiveStore, IssueStore issueStore){
        this.executiveStore = executiveStore;
        this.issueStore = issueStore;
    }
    @Override
    public boolean assignIssue(Issue issue) {

        Map<IssueType, List<Executive>> specialisationAndExecutiveMap = executiveStore.getSpecialisationAndExecutivesMap();

        for(Map.Entry<IssueType, List<Executive>> entry: specialisationAndExecutiveMap.entrySet()){
            IssueType specialisation = entry.getKey();
            List<Executive> executiveList = entry.getValue();

            // assign the issue to specialised team
            if(specialisation == issue.getIssueType()){
                for(Executive executive: executiveList){
                    if(executive.isAvailable()){
                        issue.setIssueStatus(IssueStatus.ASSIGNED);
                        issue.setEmployeeId(executive.getEmployeeId());
                        executive.setAvailable(false);
                        return true;
                    }
                }
            } else { // assign the issue to non-specialised team
                for(Executive executive: executiveList){
                    if(executive.isAvailable()){
                        issue.setIssueStatus(IssueStatus.ASSIGNED);
                        issue.setEmployeeId(executive.getEmployeeId());
                        executive.setAvailable(false);
                        return true;
                    }
                }
            }
        }

        // no executive are available
        return false;
    }
}
