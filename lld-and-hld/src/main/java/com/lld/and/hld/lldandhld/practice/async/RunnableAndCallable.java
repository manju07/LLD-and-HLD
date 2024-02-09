package com.lld.and.hld.lldandhld.practice.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Data;

public class RunnableAndCallable {

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
                System.out.println("Runnable data = " + this.data + " current thread = " + Thread.currentThread().getName());
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
                System.out.println("Callable data = " + this.data + " current thread = " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                return (data * 100) + 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        executorServiceWithRunnable();
        executorServiceWithCallable();
    }

    private static void executorServiceWithCallable() throws InterruptedException, ExecutionException {
        printStatement();
        long startTime = System.currentTimeMillis();
        System.out.println("Executing executorServiceTest with callable");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<CallableClass> listOfCallableClasses = new ArrayList<>();
        for (Integer i = 0; i < 10; i++) {
            CallableClass callableClass = new CallableClass(i);
            listOfCallableClasses.add(callableClass);
        }
        List<Future<Integer>> futures = executorService.invokeAll(listOfCallableClasses);
        
        while (!executorService.isTerminated()) {
            executorService.shutdown();
        }

        for (int i = 0; i < futures.size(); i++) {
            System.out.println("Receive data = " + futures.get(i).get());
        }
        System.out.println("Total duration for callable = " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static void executorServiceWithRunnable() {
        long startTime = System.currentTimeMillis();
        printStatement();
        System.out.println("Executing executorServiceTest with Runnable");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            // Runnable task = () -> {
            // try {
            // Thread.sleep(3000);
            // System.out.println("data = " + " current thread = " +
            // Thread.currentThread().getName());
            // printStatement();
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // };
            executorService.submit(new RunnableClass(i));
        }
        while (!executorService.isTerminated()) {
            executorService.shutdown();
        }
        
        System.out.println("Total duration for runnable = " + (System.currentTimeMillis() - startTime) + "ms");
        printStatement();
    }

    private static void printStatement() {
        System.out.println("\n");
    }
}
