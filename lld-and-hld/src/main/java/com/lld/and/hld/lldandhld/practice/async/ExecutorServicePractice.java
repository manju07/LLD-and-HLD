package com.lld.and.hld.lldandhld.practice.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import lombok.Data;

public class ExecutorServicePractice {

    /**
     * Shared Data
     */
    @Data
    static class RunnableClass implements Runnable {
        private int data;

        public RunnableClass(int data) {
            this.data = data;
        }

        public void run() {
            try {
                printStatement();
                Thread.sleep(1000);
                System.out.println(
                        "Runnable data = " + this.data + " current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("hiding")
    static class CallableClass implements Callable<Integer> {
        private Integer data;

        public CallableClass(Integer data) {
            this.data = data;
        }

        @SuppressWarnings("finally")
        @Override
        public Integer call() throws Exception {
            try {
                printStatement();
                Thread.sleep(1000);
                System.out.println(
                        "Callable data = " + this.data + " current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                return (data * 100) + 1;
            }
        }
    }

    private static void printStatement() {
        System.out.println("\n");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        onceExecution();
        executeFrequently();
    }

    private static void executeFrequently() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new RunnableClass(1), 0, 2,
                TimeUnit.SECONDS);
        // executorService.shutdown();
    }

    private static void onceExecution() throws InterruptedException, ExecutionException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<Integer> schedule = executorService.schedule(new CallableClass(1), 1, TimeUnit.SECONDS);
        System.out.println(schedule.get());
        executorService.shutdown();
    }
}
