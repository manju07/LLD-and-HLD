package com.lld.and.hld.lldandhld.practice.stream;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

// # CostExplorer ->
// #   monthlyCostList(): Array/List of size 12 filled with cost incurred in each month of the unit year
// #   annualCost(): Total cost in a unit year

// # customer_order:
// # {
// int id;
// int custId;
// String code;
// float value;
// String durationUnit;
// long dateOfPurchase;
// # }


@Data
@AllArgsConstructor
class Order {
    int id;
    int custId;
    String code;
    float value;
    String durationUnit;
    long dateOfPurchase;
}

public class CustomerCostEstimation {

    public static Map<String, Float> annualCost(List<Order> orders, int custId) {

        if (orders == null || orders.isEmpty())
            return null;

        Map<String, Float> resultMap = new HashMap<>();

        for (Order order : orders) {

            if (order.getCustId() != custId) {
                continue;
            }

            long dateOfPurchase = order.getDateOfPurchase();
            Timestamp dopInTimestamp = new Timestamp(dateOfPurchase);

            int dopYear = dopInTimestamp.getYear();
            int dopMonth = dopInTimestamp.getMonth();

            float monthlyPriceOfTheProduct = order.getValue();

            Timestamp today = new Timestamp(System.currentTimeMillis());
            int currentYear = today.getYear();

            if (dopYear > currentYear) {
                resultMap.put(order.getCode(), 0.0f);
                continue;
            }

            if (dopYear < currentYear) {
                resultMap.put(order.getCode(), monthlyPriceOfTheProduct * 12);
                continue;
            }

            // System.out.println("dopMonth=" + dopMonth);

            resultMap.put(order.getCode(), (12 - dopMonth) * monthlyPriceOfTheProduct);
        }
        return resultMap;
    }

    public static Map<String, Float[]> monthlyCostList(List<Order> orders, int custId) {
        if (orders == null || orders.isEmpty())
            return null;

        Map<String, Float[]> resultMap = new HashMap<>();

        for (Order order : orders) {

            if (order.getCustId() != custId) {
                continue;
            }

            Float[] orderResult = new Float[12];
            Arrays.fill(orderResult, 0.0f);

            long dateOfPurchase = order.getDateOfPurchase();
            Timestamp dopInTimestamp = new Timestamp(dateOfPurchase);

            int dopYear = dopInTimestamp.getYear();
            int dopMonth = dopInTimestamp.getMonth();

            float monthlyPriceOfTheProduct = order.getValue();

            Timestamp today = new Timestamp(System.currentTimeMillis());
            int currentYear = today.getYear();

            // System.out.println("currentYear=" + currentYear);

            if (dopYear > currentYear) {
                resultMap.put(order.getCode(), orderResult);
                continue;
            }

            if (dopYear < currentYear) {
                Arrays.fill(orderResult, monthlyPriceOfTheProduct);
                resultMap.put(order.getCode(), orderResult);
                continue;
            }

            // System.out.println("dopMonth=" + dopMonth);

            for (int i = dopMonth + 1; i < 12; i++) {
                orderResult[i] = monthlyPriceOfTheProduct;
            }

            resultMap.put(order.getCode(), orderResult);
        }
        return resultMap;
    }

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();
        orders.addAll(
                Arrays.asList(
                        new Order(1, 1, "JIRA_BASIC", 100, "Monthly", System.currentTimeMillis()),
                        new Order(2, 1, "CONFLUENCE", 200, "Monthly", System.currentTimeMillis())));

        Map<String, Float[]> monthlyCostList = monthlyCostList(orders, 1);

        System.out.println("Monthly cost");
        for (Entry<String, Float[]> entry : monthlyCostList.entrySet())
            System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));

        System.out.println("\nannualCost=" + annualCost(orders, 1));
        System.out.println("\n");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            String dateString = "2024-09-21 10:00:00";
            java.util.Date date = sdf.parse(dateString);
            long millis = date.getTime();

            orders.add(new Order(1, 1, "BITBUCKET", 150, "Monthly", millis));

            monthlyCostList = monthlyCostList(orders, 1);
            System.out.println("Monthly cost");
            for (Entry<String, Float[]> entry : monthlyCostList.entrySet())
                System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));

            System.out.println("\nannualCost=" + annualCost(orders, 1));
            System.out.println("\n");

            dateString = "2026-09-21 10:00:00";
            date = sdf.parse(dateString);
            millis = date.getTime();

            orders.add(new Order(1, 1, "WIKI", 300, "Monthly", millis));

            monthlyCostList = monthlyCostList(orders, 1);
            System.out.println("Monthly cost");
            for (Entry<String, Float[]> entry : monthlyCostList.entrySet())
                System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));

            System.out.println("\nannualCost=" + annualCost(orders, 1));
            System.out.println("\n");

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
    }
}