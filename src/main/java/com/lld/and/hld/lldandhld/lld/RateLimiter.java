package com.lld.and.hld.lldandhld.lld;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

// Rate-Limit
//  Customer perspective rate limit of X requests within Y duration.
//  1 used, creditScore = 4  
//  2 used, creditScore = 3 = 4 + 3 = 7  
//  last served = 11:00,  all calls served = 11:30 - 11:31, next call 11:32
//  durationDiff = 30 minutes, timewindow = 5 minutes, 30/5 = 6, 6(windows) * 5 (credit score) = 30  credit score
//  max credit score = 10 CS , 10 - 1 = 9 

/**
 * CustomerData
 */
@ToString
class CustomerData {
    int totalRequestServed;
    long firstTimeServedTimestamp;
    long lastTimeServedTimestamp;
    int creditScore;

    public CustomerData(int totalRequestServed, long firstTimeServedTimestamp, long lastTimeServedTimestamp,
            int creditScore) {
        this.totalRequestServed = totalRequestServed;
        this.firstTimeServedTimestamp = firstTimeServedTimestamp;
        this.lastTimeServedTimestamp = lastTimeServedTimestamp;
        this.creditScore = creditScore;
    }

}

/**
 * RateLimiter
 */
public class RateLimiter {

    static final int MAX_REQUESTS_ALLOWED = 5;
    static final int TIME_WINDOW_IN_MS = 600;
    static final int MAX_CREDIT_SCORE_FOR_ONE_WINDOW = 1;
    static final int MAX_CREDIT_SCORE_ALLOWED = 3;

    static Map<Integer, CustomerData> customerMap = new HashMap<>();

    static boolean rateLimit(int customerId) {
        boolean isAllowing = true;

        long currentTime = System.currentTimeMillis();

        CustomerData customerData;

        if (!customerMap.containsKey(customerId)) {

            customerData = new CustomerData(1, currentTime, currentTime, 0);
            customerMap.put(customerId, customerData);

        } else {

            customerData = customerMap.get(customerId);
            
            long timeDiffWithFirstTimecalled = currentTime - customerData.firstTimeServedTimestamp;
            long timeDiffWithLastTimecalled = currentTime - customerData.lastTimeServedTimestamp;

            if (timeDiffWithFirstTimecalled > TIME_WINDOW_IN_MS) {

                // last served = 11:00, all calls served = 11:30 - 11:31, next call 11:32, used all credit score within 11:34,  
                // durationDiff = 30 minutes, timewindow = 5 minutes, 30/5 = 6, 6(windows) * 5
                // (credit score) = 30 credit score
                // max credit score = 10 CS , 10 - 1 = 9
                // next call = 11:36 
                // timeDiff = currentTime - customerData.lastTimeServedTimestamp;

                int totalWindowsMissed = (int) timeDiffWithLastTimecalled / TIME_WINDOW_IN_MS;

                System.out.println("totalWindowsMissed =" + totalWindowsMissed);

                int totalCreditScore = totalWindowsMissed * MAX_CREDIT_SCORE_FOR_ONE_WINDOW;

                if (totalCreditScore > MAX_CREDIT_SCORE_ALLOWED) {
                    totalCreditScore = MAX_CREDIT_SCORE_ALLOWED;
                }

                customerData.firstTimeServedTimestamp = currentTime;
                customerData.lastTimeServedTimestamp = currentTime;
                customerData.totalRequestServed = 1;
                customerData.creditScore = totalCreditScore;

            } 
            else if (customerData.totalRequestServed < MAX_REQUESTS_ALLOWED) {

                customerData.totalRequestServed++;
                customerData.lastTimeServedTimestamp = currentTime;

            } else if (customerData.creditScore > 0) {

                customerData.creditScore--;
                customerData.lastTimeServedTimestamp = currentTime;

            } else {

                isAllowing = false;
            }
        }

        System.out.println("\n\n");
        for (Map.Entry<Integer, CustomerData>  entry: customerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        return isAllowing;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7; i++) {            
            System.out.println("Request = " + i + " Customeer - 1 = " + rateLimit(1));
            System.out.println("\n\n");
        }

        System.out.println("Customeer - 2 = " + rateLimit(2));
        System.out.println("\n\n");

        Thread.sleep(1800);


        for (int i = 0; i < 10; i++) {            
            System.out.println("Request = " + i + " Customeer - 1 = " + rateLimit(1));
            System.out.println("\n\n");
        }

    }

}
