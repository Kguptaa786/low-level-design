package com.kgdev.dto;

import lombok.Builder;
import lombok.Data;
import com.kgdev.enums.IssueStatus;
import com.kgdev.enums.IssueType;

@Builder
@Data
public class IssueFilterRequest {
    private IssueType type;
    private IssueStatus status;
    private Integer pageNumber;
    private Integer pageLimit;
}
