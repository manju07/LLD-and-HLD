package com.lld.and.hld.lldandhld.practice;



import java.util.*;
import java.util.stream.Collectors;

public class MinimalCost {

    private static long minimalCost(List<Integer> size, List<Integer> cost) {
        
        if (size == null || size.isEmpty() || cost == null || cost.isEmpty()) {
            return 0;
        }
        Map<Integer, TreeMap<Integer, Integer>> hashMap = new HashMap<>();
        Map<Integer, Integer> maxHashMap = new HashMap<>();

        for (int i = 0; i < size.size(); i++) {
            Integer currentSize = size.get(i);
            Integer currentCost = cost.get(i);

            if (!hashMap.containsKey(currentSize)) {

                TreeMap<Integer, Integer> treeMap = new TreeMap<>();
                treeMap.put(i, currentCost);
                hashMap.put(currentSize, treeMap);
                maxHashMap.put(currentSize, currentCost);

            } else {
                TreeMap<Integer, Integer> treeMap = hashMap.get(currentSize);
                treeMap.put(i, currentCost);

                if (currentCost > maxHashMap.get(currentSize)) {
                    maxHashMap.put(currentSize, currentCost);
                }
            }
        }

        long minimalCostAmount = 0;

        for (Map.Entry<Integer, TreeMap<Integer, Integer>> entry : hashMap.entrySet()) {
            Integer currentSize = entry.getKey();
            TreeMap<Integer, Integer> treeMap =  entry.getValue();
//            TreeMap<Integer, Integer> treeMap1 = new TreeMap<>((k1, k2) -> {
//               if (treeMap.get(k1) > treeMap.get(k2)) {
//                   return -1;
//               }
//               return
//            });

//            LinkedHashMap<Integer, Integer> linkedHashMap = treeMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(tempEntry -> tempEntry.getKey(), tempEntry -> tempEntry.getValue(), (k1, k2) -> k1, LinkedHashMap::new));

            for (Map.Entry<Integer, Integer> linkedEntry : treeMap.entrySet()) {

                if (maxHashMap.get(currentSize) == linkedEntry.getValue()) {
                    continue;
                }

                minimalCostAmount += ((long) linkedEntry.getKey() * linkedEntry.getValue());
            }
        }

        return minimalCostAmount;
    }
    public static void main(String[] args) {
        List<Integer> size = Arrays.asList(2, 3, 3, 3, 2);
        List<Integer> cost = Arrays.asList(2, 4, 5, 1, 1);
        System.out.println(minimalCost(size, cost));
    }
}