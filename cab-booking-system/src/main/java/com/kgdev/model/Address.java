package com.kgdev.model;

import lombok.Data;

@Data
public class Address {
    private String id;
    private String street;
    private String city;
    private int pinCode;
}
