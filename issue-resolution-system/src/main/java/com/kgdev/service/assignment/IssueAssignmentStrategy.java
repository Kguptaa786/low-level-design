package com.kgdev.service.assignment;

import com.kgdev.model.Issue;

public interface IssueAssignmentStrategy {

    boolean assign(Issue issue);
}
