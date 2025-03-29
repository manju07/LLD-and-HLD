package com.lld.and.hld.lldandhld.practice.stream;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Testing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        int[] arr = list.stream().mapToInt(data -> data).toArray();

        System.out.println("Primitive list");
        for (int data : arr) {
            System.out.println(data);
        }

        list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        System.out.println("Wrapper list");
        for (Integer data : list) {
            System.out.println(data);
        }

        List<List<Integer>> listOfList = Arrays.asList(list, list);

        supplierTest(() -> {
            return 1;
        });

        int[] arr2 = {1, 2, 9, 3, 4, 5, 6};
        List<Integer> result = IntStream.of(arr2).boxed().collect(Collectors.toList());
        System.out.println(result);


        Collections.sort(result, (x, y) -> y - x);
        Collections.sort(result, Comparator.reverseOrder());
        result.sort(Comparator.reverseOrder());
        System.out.println(result);


        int[] array = result.stream().mapToInt(data -> data).toArray();
        System.out.println("Arrays.toString=" + Arrays.toString(array));

        List<List<Integer>> listOfList2 = Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(4, 3, 5, 7), Arrays.asList(8, 9, 1, 2));
        List<Integer> singleList = listOfList2.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(singleList);
        int[] resultList = listOfList2.stream().flatMapToInt(list2 -> list2.stream().flatMapToInt(IntStream::of)).sorted().toArray();
        System.out.println(Arrays.toString(resultList));

        Optional<String> optString = Optional.empty();
        optString = Optional.of("data");
        optString.ifPresent(System.out::println);
        optString = Optional.empty();
        System.out.println(optString.orElseGet(() -> "else-data"));

        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> "data1");
        completableFuture.thenAcceptBoth(CompletableFuture.supplyAsync(() -> "data2"), (dat1, data2) -> {
            System.out.println("CompletableFuture="+ dat1 + " " + data2);
        }).get();

        completableFuture.thenAccept(System.out::println);
        Object o = completableFuture.thenApply(data -> data).get();

    }

    public static void supplierTest(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }
}
