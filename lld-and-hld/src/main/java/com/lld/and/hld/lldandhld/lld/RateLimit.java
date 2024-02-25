/**
* 		Should be able to accept required(TPS) transactions per second or the rate.
* 		Should drop the transactions if it exceeds our defined rate.
* 		Should work in concurrent situations.


* 		Should be able to smooth bursts of requests. For example if we have defined TPS as 5, and all five requests arrive at the same moment, it should be able to line them up at fixed intervals, i.e. execute each of them at an interval of 200ms. It requires an internal timing circuit.
* 		If we have a TPS of 5, and in one of the 1 second slots, we use only three tokens for the next second, we should be capable of providing 5+2 = 7 tokens as a bonus. But at the rate of 1/7 (142.28ms) per token. The bonus should not carry forward to the next slot.
**/
/**
1s - 5 Request

TIME_WINDOW: 1S
MAX_REQUEST: 5

HashMap
    Key: customerId

    Value
    {
        firstTimeCalled: timestamp
        lastTimeCalled: timestamp
        totalServedRequestsCount: int 
    }

**/

package com.lld.and.hld.lldandhld.lld;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Data;

/**
 * RateLimit
 */
public class RateLimit {

    static Map<Integer, CustomerData> map = new HashMap<>();

    static int MAX_REQUEST = 5;
    static long TIME_WINDOW = 1000;

    @Data
    static class CustomerData {
        private Timestamp firstTimeCalled;
        private Timestamp lastTimeCalled;
        private int totalRequestsServed;
    }

    static synchronized boolean rateLimit(int customerId) {

        long currentTimeInLong = System.currentTimeMillis();
        Timestamp currentTimestamp = new Timestamp(currentTimeInLong);

        if (!map.containsKey(customerId)) {
            CustomerData customerData = new CustomerData();
            customerData.setFirstTimeCalled(currentTimestamp);
            customerData.setLastTimeCalled(currentTimestamp);
            customerData.setTotalRequestsServed(1);
            map.put(customerId, customerData);

        } else {

            CustomerData customerData = map.get(customerId);

            Long diffTimeInLong = currentTimeInLong - customerData.getFirstTimeCalled().getTime();

            if (diffTimeInLong > TIME_WINDOW) {
                customerData.setFirstTimeCalled(currentTimestamp);
                customerData.setLastTimeCalled(currentTimestamp);
                customerData.setTotalRequestsServed(1);
                return true;
            }

            if (customerData.getTotalRequestsServed() >= MAX_REQUEST) {
                return false;
            }

            customerData.setLastTimeCalled(currentTimestamp);
            customerData.setTotalRequestsServed(customerData.getTotalRequestsServed() + 1);
        }

        return true;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Boolean>> listOfCallables = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listOfCallables.add(() -> rateLimit(1));
        }

        List<Future<Boolean>> listOfFutures = executorService.invokeAll(listOfCallables);

        for (Future<Boolean> future : listOfFutures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
        while (!executorService.isShutdown()) {
            executorService.shutdown();
        }

        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // System.out.println(rateLimit(1));
        // Thread.sleep(1000);
        // System.out.println(rateLimit(1));
    }
}