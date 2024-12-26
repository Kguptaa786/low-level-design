package com.kgdev.model;

import com.kgdev.enums.PayMode;
import lombok.Data;

@Data
public class Payment {
    private String id;
    private double amount;
    private PayMode payMode;
}
