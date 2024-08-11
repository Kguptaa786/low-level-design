package org.example.model;

import lombok.Data;
import org.example.enums.IssueType;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class Executive {
    private final UUID id;
    private final String name;
    private final String employeeId;
    private final IssueType specialisation;
    private final AtomicBoolean isAvailable;
    private Executive(String name, String employeeId, IssueType specialisation, AtomicBoolean isAvailable) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.employeeId = employeeId;
        this.specialisation = specialisation;
        this.isAvailable = isAvailable;
    }

    public static Executive create(String name, String employeeId, IssueType specialisation){
        return new Executive(name, employeeId, specialisation, new AtomicBoolean(true));
    }
}
