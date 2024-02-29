package com.lld.and.hld.lldandhld.practice.generic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericClass<T extends Number> {
    private T t1;
    private T t2;

    public GenericClass(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public Double sumOfNumbers() {
        return t1.doubleValue() + t2.doubleValue();
    }

    // upper bounded wildcard (? extends SuperClass)
    public static void printNumbersWithUpperBound(List<? extends Number> list) {
        System.out.println("printNumbersWithUpperBound");
        for (Number number : list) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    // Unbounded wildcard (?)
    public static void print(List<?> list) {
        System.out.println(" Unbounded wildcard - print");
        for (Object object : list) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    // lower bounded wildcard (? extends SuperClass)
    public static void printNumbersWithLowerBound(List<? super Integer> list) {
        System.out.println("printNumbersWithLowerBound");
        for (Object number : list) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public static void printMapWithUpperBound(Map<?, ?> map) {
        System.out.println("Printing Map\n");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
    }

    public static <T extends String> List<Integer> convert(List<T> inputList) {
        return inputList.stream().map(data -> Integer.parseInt(data)).collect(Collectors.toList());
    }

    public static <E> void printPrimitiveTypeArray(E[] arr) {
        System.out.println("print primitive type array");
        for (E e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public T getT1() {
        return t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }
}
