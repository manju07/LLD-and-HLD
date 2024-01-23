package com.lld.and.hld.lldandhld.practice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

public class StreamNewTest {

    @Setter
    @Getter
    static class Employee {
        int id;
        String name;
        double salary;
        String dept;
        List<String> skills;

        public Employee(int id, String name, Double salary, String dept, List<String> skills) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.dept = dept;
            this.skills = skills;
        }

        @Override
        public String toString() {
            return "Employee [dept=" + dept + ", id=" + id + ", name=" + name + ", salary=" + salary + ", skills="
                    + skills + "]";
        }
    }

    public static void main(String[] args) {

        // List<List<Integer>> listOfList1 = new ArrayList<>();
        // listOfList1.addAll(Arrays.asList(Arrays.asList(6,4,10)));

        List<List<Integer>> listOfList = Arrays.asList(Arrays.asList(4, 5, 1, 4, 10), Arrays.asList(14, 5, 7, 4, 1));
        System.out.println(listOfList);

        System.out.println("Desc sorted listOfList = " + listOfList.stream()
                .map(list -> list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))
                .collect(Collectors.toList()));

        System.out.println("Asc sorted listOfList = " + listOfList.stream()
                .map(list -> list.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toList()));

        // Convert Collections to Primitive
        double[][] doublesOfDoubles = listOfList.stream()
                .map(list -> list.stream().mapToDouble(data -> Double.valueOf(data)).toArray())
                .toArray(double[][]::new);
        System.out.println("\n\ncollections to primitive");
        for (int i = 0; i < doublesOfDoubles.length; i++) {
            for (int j = 0; j < doublesOfDoubles[i].length; j++) {
                System.out.print(doublesOfDoubles[i][j] + " ");
            }
            System.out.println();
        }

        List<List<Double>> listOfListDoubles = Arrays.stream(doublesOfDoubles)
                .map(doubles -> Arrays.stream(doubles).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println("\n\nprimitive to collections");
        for (int i = 0; i < listOfListDoubles.size(); i++) {
            for (int j = 0; j < listOfListDoubles.get(i).size(); j++) {
                System.out.print(listOfListDoubles.get(i).get(j) + " ");
            }
            System.out.println();
        }

        List<Double> collect = listOfListDoubles.stream().flatMap(list -> list.stream().flatMap(Stream::of))
                .collect(Collectors.toList());
        System.out.println("FlatMap = "
                + collect);

        double[] doubleArr = listOfListDoubles.stream().flatMap(list -> list.stream().flatMap(data -> Stream.of(data)))
                .mapToDouble(Double::doubleValue).toArray();
        System.out.print("DoubleArr = ");
        for (int i = 0; i < doubleArr.length; i++) {
            System.out.print(doubleArr[i] + " ");
        }
        System.out.println();

        System.out.println("ListOfDoubles = " + Arrays.stream(doubleArr).boxed().collect(Collectors.toList()));

        System.out.println("\nEach value doubled =  " + listOfListDoubles.stream()
                .flatMap(list -> list.stream().flatMap(Stream::of))
                .map(data -> data * 2).collect(Collectors.toList()));

        List<Employee> listOfEmployees = new ArrayList<Employee>();
        addEntriesToListOfEmployees(listOfEmployees);

        System.out.println("\n\nGrouping by Dept " + listOfEmployees.stream().collect(
                Collectors.groupingBy(Employee::getDept, () -> new TreeMap<>(),
                        Collectors.mapping(Employee::getId, Collectors.toList()))));

        System.out.println("\n\nPartitioning = " +
                Stream
                        .iterate(1, (data) -> data + 1)
                        .limit(100)
                        .collect(Collectors.partitioningBy(data -> data % 2 == 0)));

        System.out.println();
        // sorting(listOfList);
    }

    private static void addEntriesToListOfEmployees(List<Employee> listOfEmployees) {
        listOfEmployees.add(new Employee(1, "Manju", 10000D, "DEV", Arrays.asList("Programming", "Writing")));
        listOfEmployees.add(new Employee(2, "Soda", 12000D, "QA",
                Arrays.asList("Programming", "Coding", "Writing", "Swimming", "E", "F", "G")));
        listOfEmployees.add(new Employee(3, "Netra", 16000D, "QA", Arrays.asList("Swimming", "Writing")));
        listOfEmployees.add(
                new Employee(4, "Vishwa", 20000D, "DEV", Arrays.asList("Programming", "Coding", "A", "B", "C", "D")));
        listOfEmployees.add(new Employee(5, "Shivu", 25000D, "DEV", Arrays.asList("Writing", "Swimming")));
    }

    private static void sorting(List<List<Integer>> listOfList) {
        listOfList.get(0).sort((data1, data2) -> data1 >= data2 ? -1 : 1);
        Collections.sort(listOfList.get(1), (data1, data2) -> data1 >= data2 ? -1 : 1);

        System.out.println(listOfList);
    }
}
