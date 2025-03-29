package com.lld.and.hld.lldandhld.practice.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice2025 {

    public static void main(String[] args) {
        List<List<Integer>> listOfList = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));

        int[][] twoDArr = listOfList.stream().map(list -> list.stream().mapToInt(data -> data).toArray())
                .toArray(int[][]::new);

        for (int[] arr : twoDArr) {
            System.out.println(Arrays.toString(arr));
        }

        List<List<Integer>> data = Arrays.stream(twoDArr)
                .map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(data);

        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        LinkedHashMap<String, Integer> linkedHashMap = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap((entry) -> entry.getKey(), entry -> entry.getValue(),
                        (oldValue, newValue) -> newValue, () -> new LinkedHashMap<>()));
                        
        System.out.println(linkedHashMap);

        linkedHashMap = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap((entry) -> entry.getKey(), entry -> entry.getValue(),
                        (oldValue, newValue) -> newValue, () -> new LinkedHashMap<>()));

        System.out.println(linkedHashMap);
    }
}
