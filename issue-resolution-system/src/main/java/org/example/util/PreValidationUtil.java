package org.example.util;

import org.example.model.Issue;

public class PreValidationUtil {
    public static void validateIssueRequest(Issue issue){
        String description = issue.getDescription();
        if(description.length() < 5 || description.length() > 200){
            throw new IllegalArgumentException("Description length must be greater than 30 and less than 200.");
        }
        String orderId = issue.getOrderId();
        if(orderId.length() < 3 || orderId.length() > 15){
            throw new IllegalArgumentException("Order id is invalid. Please provide correct order id.");
        }
    }
}
