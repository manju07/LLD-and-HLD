package com.lld.and.hld.lldandhld.practice.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuturePractice {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        supplyAsyncTest();

        runAsyncTest();

        exceptionHandling();

        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("12345");
        String result = completableFuture.get();
        System.out.println(result);

        thenApplyTest(completableFuture);

        thenAcceptTest(completableFuture);

        thenAcceptBothTest();

        thenComposeTest(completableFuture);

        thenCombineTest(completableFuture);

        supplyAsyncAllOf();
    }

    private static void thenAcceptBothTest() throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing thenAcceptBothTest");

        CompletableFuture<Void> thenAcceptBoth = CompletableFuture.supplyAsync(() -> "123")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> "1234"), (data1, data2) -> {

                    System.out.println(data1 + " " + data2);
                });
        System.out.println("Executing After thenAcceptBoth");
        thenAcceptBoth.get();
        printStatement();
    }

    private static void exceptionHandling() throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing exceptionHandling");
        Integer intResult = CompletableFuture.supplyAsync(() -> 10 / 0)
                .handleAsync((data, exception) -> {
                    if (exception != null) {
                        System.out.println("handleAsync exception:" + exception);
                    }
                    return data / 0;
                })
                .exceptionally((exception) -> {
                    System.out.println("exceptionally handled=" + exception);
                    return 10;
                })
                .get();
        System.out.println("intResult:" + intResult);
        printStatement();
    }

    private static void runAsyncTest() throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing runAsyncTest");
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("run async");
        });

        System.out.println("executed before run async");
        System.out.println("RunAsync Returns null:" + completableFuture2.get());
        printStatement();
    }

    private static void printStatement() {
        System.out.println("\n");
    }

    private static void supplyAsyncAllOf() throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing supplyAsyncAllOf");
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> completableFutureAllOf = CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> {
            System.out.println("11 = " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 1;
        }), CompletableFuture.supplyAsync(() -> {
            System.out.println("12 = " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 2;
        }), CompletableFuture.supplyAsync(() -> {
            System.out.println("13 = " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 3;
        }));

        completableFutureAllOf.handle((result, exception) -> {
            if (exception != null) {
                System.out.println("Error = " + exception);
                return null;
            }
            return result;
        })
                .get();
        long endTime = System.currentTimeMillis();
        System.out.println("Total duration = " + ((endTime - startTime)) + " ms");
        printStatement();
    }

    private static void supplyAsyncTest() throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing supplyAsyncTest");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "12345";
        });

        System.out.println(completableFuture.get());
        printStatement();
    }

    private static void thenComposeTest(CompletableFuture<String> completableFuture)
            throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing thenComposeTest");

        CompletableFuture<Integer> thenComposeCF = completableFuture
                .thenCompose(data -> CompletableFuture.supplyAsync(() -> Integer.parseInt(data) + 1234));

        System.out.println(thenComposeCF.get());

        printStatement();
    }

    private static void thenAcceptTest(CompletableFuture<String> completableFuture)
            throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing thenAcceptTest");

        CompletableFuture<Void> thenAcceptCF = completableFuture
                .thenAccept(data -> System.out.println("thenAccept output:" + data));
        System.out.println("executed after thenAccept method");

        thenAcceptCF.get();
        printStatement();
    }

    private static void thenCombineTest(CompletableFuture<String> completableFuture)
            throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing thenCombineTest");
        CompletableFuture<Integer> completableCombineResult = completableFuture
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    System.out.println("2 = " + Thread.currentThread().getName());
                    return "98765";
                }), (data1, data2) -> {

                    System.out.println("3 = " + Thread.currentThread().getName());
                    return Integer.parseInt(data1) + Integer.parseInt(data2);
                });

        System.out.println(completableCombineResult.get());
    }

    private static void thenApplyTest(CompletableFuture<String> completableFuture)
            throws InterruptedException, ExecutionException {
        printStatement();
        System.out.println("Executing thenApplyTest");
        CompletableFuture<Integer> completableInt = completableFuture
                .thenApply(data -> data + "4")
                .thenApply(data -> data + "5")
                .thenApply(Integer::parseInt);
        System.out.println(completableInt.get());
        printStatement();
    }
}
