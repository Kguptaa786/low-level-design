package org.example.services.assignment;

import org.example.model.Issue;

public interface IssueAssignmentStrategy {

    boolean assign(Issue issue);
}
