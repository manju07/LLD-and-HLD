package com.lld.and.hld.lldandhld.practice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.s;

public class Testing {

    public static void main(String[] args) {
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
    }

    public static void supplierTest(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }
}
