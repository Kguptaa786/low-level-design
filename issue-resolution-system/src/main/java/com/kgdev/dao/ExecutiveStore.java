package com.kgdev.dao;

import com.kgdev.enums.IssueType;
import com.kgdev.model.Executive;

import java.util.List;

public interface ExecutiveStore {
    Executive create(Executive executive);
    boolean update(Executive executive);
    List<Executive> findBySpecialisation(IssueType issueType);
    List<Executive> findAllNotEqualSpecialisation(IssueType issueType);
}
