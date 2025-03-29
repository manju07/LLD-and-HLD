package com.lld.and.hld.lldandhld.practice.stream;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

// # CostExplorer ->
// #   monthlyCostList(): Array/List of size 12 filled with cost incurred in each month of the unit year
// #   annualCost(): Total cost in a unit year

// #   customer -> subscribe to multi products (JIRA)

// #     JIRA:
    
// # - Product:
// #     - multiple Offers : basic, essential, advanced
// #         - chargeInformation: $100, $150, $400

// # - offer
// # {
// #     id: "1",
// #     name: "JIRA Basic",
// #     description: "some text",
// #     productCode: "JIRA_BASIC",
// #     "chargeInformation": {
// #         total: 100,
// #         durationUnit: "Monthly"
// #     }
// # }

// # customer_order:
// # {
// #     id: 1434,
// #     customerId: 123,
// #     offerId: 1,
// #     productCode: "JIRA_BASIC",
// #     dateOfPurchase: timestamp
// # }

// # Jan - Dec

// # - monthlyCostList() -> {{cost, Month}, 0, 0, {100, April}, ..... {100, Dec}} => 900
// # - annualCost() -> 900
    
@Data
@AllArgsConstructor
class Order {
    int id;
    String code; 
    float value;
    String durationUnit;
    long dateOfPurchase;
}

public class CustomerCostEstimation {

    public static Float annualCost(Order order) {
        if (order == null) {
            return null;
        }
        
        Float annualValue = 0.0f;

        long dateOfPurchase = order.getDateOfPurchase();
        float value = order.getValue();
        Date date = new Date(dateOfPurchase);

        int year = date.getYear();
        int currentYear = new Date(System.currentTimeMillis()).getYear();
        
        if (year > currentYear) {
            return 0.0f;
        } else if (year < currentYear) {
            return 12 * value;
        }

        int month = date.getMonth();

        for (int i = month; i <= 11; i++) {
            annualValue += value;
        }
        return annualValue;
    }

    public static Map<Integer, Float> monthlyCostList(Order order) {
        if (order == null) {
            return null;
        }
        Map<Integer, Float> resultMap = new HashMap<>();
        
        long dateOfPurchase = order.getDateOfPurchase();
        float value = order.getValue();

        Date date = new Date(dateOfPurchase);

        int year = date.getYear();
        int month = date.getMonth();

        int currentYear = new Date(System.currentTimeMillis()).getYear();
        
        System.out.println("year=" + year +  " currentYear="+currentYear);

        if (year < currentYear) {

            for (int i = 1; i <= 12; i++) {
                resultMap.put(i, value);
            }
            return resultMap;

        } else if (year > currentYear) {

            for (int i = 1; i <= 12; i++) {
                resultMap.put(i, 0.0f);
            }
            return resultMap;
        }

        
        System.out.println("Month=" + month);

        for (int i = 1; i <= month; i++) {
            resultMap.put(i, 0.0f);
        }

        for (int i = month + 1; i <= 12; i++) {
            resultMap.put(i, value);
        }
        return resultMap;
    }


    public static void main(String[] args) {
        Order order = new Order(1, "JIRA_BASIC", 100, "Monthly", System.currentTimeMillis());
        
        System.out.println("monthlyCostList=" + monthlyCostList(order));
        System.out.println("annualCost=" + annualCost(order));
        System.out.println("\n\n");

        String dateString = "2024-09-21 10:00:00"; // Example date string
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            java.util.Date date = sdf.parse(dateString);
            long millis = date.getTime();

            Order order2 = new Order(1, "JIRA_BASIC", 100, "Monthly", millis);

            System.out.println("monthlyCostList=" + monthlyCostList(order2));
            System.out.println("annualCost=" + annualCost(order2));

            System.out.println("\n\n");
            
            dateString = "2026-09-21 10:00:00"; // Example date string
            date = sdf.parse(dateString);
            millis = date.getTime();

            order2 = new Order(1, "JIRA_BASIC", 100, "Monthly", millis);

            System.out.println("monthlyCostList=" + monthlyCostList(order2));
            System.out.println("annualCost=" + annualCost(order2));

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

    }    
}