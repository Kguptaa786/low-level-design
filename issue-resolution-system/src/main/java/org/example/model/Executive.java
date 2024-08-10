package org.example.model;

import lombok.Data;
import org.example.enums.IssueType;

import java.util.UUID;

@Data
public class Executive {
    private UUID id;
    private String name;
    private String employeeId;
    private IssueType specialisation;
    private boolean isAvailable;

    public Executive(String name, String employeeId, IssueType specialisation) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.employeeId = employeeId;
        this.specialisation = specialisation;
        this.isAvailable = true;
    }
}
