package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.enums.IssueStatus;
import org.example.enums.IssueType;

@Builder
@Data
public class IssueFilterRequest {
    private IssueType type;
    private IssueStatus status;
    private Integer pageNumber;
    private Integer pageLimit;
}
