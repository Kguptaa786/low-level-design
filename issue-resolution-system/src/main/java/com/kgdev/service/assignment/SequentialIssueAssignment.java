package com.kgdev.service.assignment;

import com.kgdev.dao.ExecutiveStore;
import com.kgdev.dao.IssueStore;
import com.kgdev.enums.IssueStatus;
import com.kgdev.model.Executive;
import com.kgdev.model.Issue;

import java.util.List;


public class SequentialIssueAssignment implements IssueAssignmentStrategy {
    private final ExecutiveStore executiveStore;
    private final IssueStore issueStore;
    public SequentialIssueAssignment(ExecutiveStore executiveStore, IssueStore issueStore){
        this.executiveStore = executiveStore;
        this.issueStore = issueStore;
    }
    @Override
    public boolean assign(Issue issue) {
        List<Executive> specialisedExecutives = executiveStore.findBySpecialisation(issue.getType());
        List<Executive> nonSpecialisedExecutives = executiveStore.findAllNotEqualSpecialisation(issue.getType());

        Executive executive = specialisedExecutives.stream()
                .filter(e -> e.getIsAvailable().get())
                .findFirst()
                .orElse(null);

        if (executive == null) {
            executive = nonSpecialisedExecutives.stream()
                    .filter(e -> e.getIsAvailable().get())
                    .findFirst()
                    .orElse(null);
        }

        if (executive == null && !specialisedExecutives.isEmpty()) {
            int randomIndex = (int) (Math.random() * specialisedExecutives.size());
            executive = specialisedExecutives.get(randomIndex);
        }

        if (executive != null) {
            executive.getIsAvailable().compareAndSet(true, false);
            issue.setEmployeeId(executive.getEmployeeId());
            issue.setStatus(IssueStatus.ASSIGNED);
            executiveStore.update(executive);
            return true;
        }

        return false;
    }
}
