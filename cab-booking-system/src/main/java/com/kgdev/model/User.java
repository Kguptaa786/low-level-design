package com.kgdev.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class User {
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private String mobileNumber;
    private Timestamp createdAt;
}
