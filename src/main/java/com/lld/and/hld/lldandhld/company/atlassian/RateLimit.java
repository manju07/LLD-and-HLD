package com.lld.and.hld.lldandhld.company.atlassian;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Perform rate limiting logic for provided customer ID. Return true if the
 * // request is allowed, and false if it is not.
 * // boolean rateLimit(int customerId)
 * // Each customer can make X requests per Y seconds
 * 
 * HashMap (cID, Object)
 * {
 * totalRequestsCount: 0,
 * firstTimeCalled: timestamp,
 * lastTimeCalled: timestamp
 * }
 */

class CustomerData {
    int totalRequestCount;
    Timestamp firstTimeCalled, lastTimeCalled;

    CustomerData(Timestamp firstTimeCalled, Timestamp lastTimeCalled) {
        totalRequestCount = 1;
        this.firstTimeCalled = firstTimeCalled;
        this.lastTimeCalled = lastTimeCalled;
    }
}

public class RateLimit {
    // 5 - A, 5 - R
    final static int MAX_REQUESTS = 3;
    final static long DURATION_IN_MILLI_SECONDS = 200;
    static Map<Integer, CustomerData> map = new HashMap<>();

    public static Boolean rateLimit(int customerId) {
        CustomerData customerData = null;
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (map.containsKey(customerId)) {

            customerData = map.get(customerId);

            Timestamp firstCalled = customerData.firstTimeCalled;
            long totalDuration = currentTimestamp.getTime() - firstCalled.getTime();

            // 20S, 1-5, 6-10, 11-15, 15-20, 20-24
            if (totalDuration > DURATION_IN_MILLI_SECONDS) {

                customerData.firstTimeCalled = currentTimestamp;
                customerData.totalRequestCount = 1;
                return true;

            } else if (customerData.totalRequestCount >= MAX_REQUESTS) {
                return false;
            } else {
                customerData.totalRequestCount++;
                customerData.lastTimeCalled = currentTimestamp;
            }
        } else {
            customerData = new CustomerData(currentTimestamp, currentTimestamp);
        }

        map.put(customerId, customerData);
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println(rateLimit(1));
            System.out.println(rateLimit(1));
            System.out.println(rateLimit(1));
            System.out.println(rateLimit(1));
            Thread.sleep(200);
            // Thread.sleep(50);
            System.out.println(rateLimit(1));
            System.out.println(rateLimit(1));
        } catch (Exception e) {
            System.err.println(e);
            // TODO: handle exception
        }
    }
}