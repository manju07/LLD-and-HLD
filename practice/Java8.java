package com.spring.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8 {
    public static void main(String[] args) {
        // functionalInterfaceMethod();

        // System.out.println();

        // List<Integer> list = predicateMethod();

        // System.out.println();

        // Function<Integer, String> function = (tempData) -> tempData + ", ";
        // list.stream().map(function).filter((Predicate<String>) (tempData) -> tempData
        // instanceof String)
        // .collect(Collectors.toList()).forEach(System.out::print);

        // System.out.println();

        // System.out.println(list.stream().anyMatch((Predicate<Integer>) (tempData) ->
        // tempData < 0));

        // System.out.println();

        // twoDListSort();

        // System.out.println();

        // sumOfNumbers();

        // optionalExample();

        // sortInParallelStreamOrder();
        // sortInSequentialOrder();
        // mapExample();
        List<List<Integer>> listOfList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 4),
                Arrays.asList(6, 7));

        int data[][] = collectionToPrimitive(listOfList);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        Arrays.sort(data, (a,b) -> a[0] - b[0]);
        System.out.println("After sort");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] collectionToPrimitive(List<List<Integer>> listOfList) {
        Collections.sort(listOfList, (a, b) ->  b.get(0)- a.get(0));
        return  listOfList.stream().map(list -> list.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
    }

    public static void mapExample() {
        System.out.println("Java8.mapExample()");
        Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
            {
                put(1, 10);
                put(2, 20);
                put(3, 30);
                put(4, 40);
                put(5, 50);
            }
        };
        Map<Integer, Integer> resultMap = map.entrySet().parallelStream().filter(entry -> ((entry.getKey() % 2) == 0))
                .collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue()));
        resultMap.putIfAbsent(6, 60);

        resultMap.computeIfPresent(6, (key, value) -> value + 10);
        resultMap.computeIfAbsent(8, (key) -> 20);

        resultMap.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void optionalExample() {
        Optional<String> optData = Optional.of("test1");
        optData.orElse("test2");
        optData.ifPresent(System.out::print);
    }

    private static void sumOfNumbers() {
        List<Integer> tempList = Arrays.asList(1, 2, 1, 2, 1, 3, 4);
        System.out.println("sum of numbers = "
                + tempList.stream().sorted((d1, d2) -> d2.compareTo(d1)).reduce((d1, d2) -> d1 + d2).get());
    }

    private static void twoDListSort() {
        List<List<Integer>> twoDList = new ArrayList<List<Integer>>() {
            {
                add(Arrays.asList(4, 7));
                add(Arrays.asList(3, 7));
                add(Arrays.asList(1, 5));
                add(Arrays.asList(3, 9));
            }
        };
        // twoDList.add(Arrays.asList(4, 7));
        // twoDList.add(Arrays.asList(3, 7));
        // twoDList.add(Arrays.asList(1, 5));
        // twoDList.add(Arrays.asList(3, 9));

        twoDList.sort((t1, t2) -> t2.get(0).compareTo(t1.get(0)));
        System.out.println(twoDList);
    }

    private static List<Integer> predicateMethod() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(3);
        list.add(4);
        list.add(5);
        list = list.stream().filter(((Predicate<Integer>) (data1) -> data1 > 0)).map(data2 -> data2 * 2)
                .collect(Collectors.toList());
        return list;
    }

    private static void functionalInterfaceMethod() {
        FunctionalInterface fi = (str) -> Integer.parseInt(str);
        int data = fi.test("123");
        System.out.println(fi.test1(data));
    }

    public static void sortInSequentialOrder() {
        System.out.println();
        System.out.println("Java8.sortInSequentialOrder()");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long startTime = System.nanoTime();
        Long count = list.stream().sorted((data1, data2) -> data2.compareTo(data1)).count();
        long endTime = System.nanoTime();
        System.out.println("Total Duration in Milli = " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
    }

    public static void sortInParallelStreamOrder() {
        System.out.println();
        System.out.println("Java8.sortInParallelStreamOrder()");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(UUID.randomUUID().toString());
        }
        long startTime = System.nanoTime();
        long count = list.parallelStream().sorted((data1, data2) -> data2.compareTo(data1)).count();
        long endTime = System.nanoTime();
        System.out.println("Total Duration in Milli = " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
    }

}