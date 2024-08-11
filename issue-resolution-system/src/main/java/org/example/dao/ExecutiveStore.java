package org.example.dao;

import org.example.enums.IssueType;
import org.example.model.Executive;

import java.util.List;

public interface ExecutiveStore {
    Executive create(Executive executive);
    boolean update(Executive executive);
    List<Executive> findBySpecialisation(IssueType issueType);
    List<Executive> findAllNotEqualSpecialisation(IssueType issueType);
}
