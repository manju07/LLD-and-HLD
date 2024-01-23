package com.lld.and.hld.lldandhld.practice.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.lld.and.hld.lldandhld.generic.GenericClass;

/**
 * MapWithStream
 */
public class MapWithStream {

    public static void mapWithStream() {
        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("1", 10);
                put("3", 30);
                put("2", 20);
                put("5", 50);
                put("7", 1);
                put("8", 3);
            }
        };

        GenericClass.printMapWithUpperBound(map);

        sortByValue(new HashMap<>(map));
        sortByValue1(new HashMap<>(map));

        filterEvenValuesMap(new HashMap<>(map));

        FlipTheMap(new HashMap<>(map));
    }

    private static void FlipTheMap(Map<String, Integer> map) {

        Function<Map.Entry<String, Integer>, Integer> keyMapper = (entry) -> {
            return entry.getValue();
        };

        Function<Map.Entry<String, Integer>, String> valueMapper = (entry) -> {
            return entry.getKey();
        };

        Map<Integer, String> flippedMap = map.entrySet().stream().collect(Collectors.toMap(keyMapper, valueMapper));
        System.out.println("flipped Map = " + flippedMap);
    }

    private static void filterEvenValuesMap(Map<String, Integer> map) {
        Map<String, Integer> evenMap = map.entrySet().stream().filter(entry -> (entry.getValue() % 2 == 0))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        System.out.println("filtered entries by even values = " + evenMap);
    }

    private static void sortByValue(Map<String, Integer> map) {
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>((entry1, entry2) -> {
            if (map.get(entry1) >= map.get(entry2)) {
                return 1;
            } else if (map.get(entry1) < map.get(entry2)) {
                return -1;
            }
            return 0;
        });
        treeMap.putAll(map);
        System.out.println("Sorted by values = " + treeMap);
    }

    public static void sortByValue1(Map<String, Integer> map) {

        TreeMap<String, Integer> resultMap = map.entrySet().stream()
                .collect(Collectors.toMap((entry1) -> entry1.getKey(), (entry1) -> entry1.getValue(),
                        (oldValue, newValue) -> newValue,
                        () -> new TreeMap<>((entry1, entry2) -> map.get(entry1) > map.get(entry2) ? 1 : -1)));

        System.out.println("Solution 2,  Sorted map with stream = " + resultMap);
    }

    public static void main(String[] args) {
        mapWithStream();
    }
}