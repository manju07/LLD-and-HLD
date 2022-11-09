package com.lld.and.hld.lldandhld.practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StreamPractice2 {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @lombok.Builder
    @EqualsAndHashCode
    static class Employee {
        private Long id;
        private String name;
        private Double salary;
    }

    public static void main(String[] args) throws Exception {
        streamBuilder();
        
        List<Employee> employees = getEmployees();
        maxSalary(employees);

        sumOfSalary(employees);

        summarizingStatistics(employees);

        partitioningBy(employees);

        groupingBy(employees);

        performanceTest();
    }

    private static List<Employee> getEmployees() {
        Employee employee1 = new Employee(1L, "Manju", 100D);
        Employee employee2 = new Employee(2L, "Manju", 200D);
        Employee employee3 = new Employee(3L, "Manju", 300D);
        return Arrays.asList(employee1, employee2, employee3);
    }

    private static void maxSalary(List<Employee> employees) throws Exception {
        Double maxSalary = employees.stream().mapToDouble(employee -> employee.getSalary()).max()
                .orElseThrow(Exception::new);
        System.out.println("maxSalary=" + maxSalary);
    }

    private static void sumOfSalary(List<Employee> employees) {
        Double sumOfSalary = employees.stream().map(employee -> employee.getSalary()).reduce(0D,
                (data, sum) -> data + sum);
        System.out.println("sumOfSalary=" + sumOfSalary);
    }

    private static void summarizingStatistics(List<Employee> employees) {
        DoubleSummaryStatistics summaryStatistics = employees.stream().mapToDouble(employee -> employee.getSalary()).summaryStatistics();
        System.out.println(summaryStatistics);
    }

    private static void partitioningBy(List<Employee> employees) {
        long start = System.currentTimeMillis();
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(employee -> employee.getId()%2 == 0));
        System.out.println("Time taken = " + (System.currentTimeMillis() - start));
    }

    private static void groupingBy(List<Employee> employees) {
        List<List<Employee>> listOfList = Arrays.asList(employees, employees, employees);
        Map<Long, List<Employee>> groupedData = listOfList.stream().flatMap(Collection::stream)
                .collect(Collectors.groupingBy(employee -> employee.getId(), Collectors.toList()));
        groupedData.forEach((key, value) -> System.out.println("key=" + key + " value=" + value));
    }

    private static void streamBuilder() {
        Builder<Integer> builder = Stream.builder();
        builder.accept(1);
        builder.accept(2);
        builder.accept(3);
        builder.accept(4);
        builder.accept(5);
        Stream<Integer> stream = builder.build();
        List<Double> doubleDataList = stream.map(data -> data + 10D).collect(Collectors.toList());
        doubleDataList.forEach(System.out::println);
        Optional<Integer> optionalResult = Stream.of(1, 2, 3, 4).reduce((data, result) -> data + result);
        Integer result = optionalResult.get();
        System.out.println(result);
        System.out.println("\n\n\n");
    }

    private static void performanceTest() {
        long startTimeMillis = System.currentTimeMillis();
        // Stream.generate(Math::random)
        // IntStream.range(1, 1000000)
        Stream.iterate(1, i -> i++)
        // .skip(5)
        .limit(1000000) 
        // .parallel()
        .filter(data -> data % 2 == 0)
        // .collect(Collectors.toList());
        .toArray();
        // List<Integer> list1 = intStream.filter(data -> data % 2 == 0).map(data -> data * data).boxed().collect(Collectors.toList());
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("Time taken to execute(MilliSeconds) = " + (endTimeMillis - startTimeMillis));

        // startTimeMillis = System.currentTimeMillis();
        // intStream = IntStream.range(1, 1);
        // List<Integer> list2 = intStream.filter(data -> data % 2 == 0).map(data -> data * data).boxed().collect(Collectors.toList());
        // endTimeMillis = System.currentTimeMillis();
        // System.out.println("Time taken to execute(MilliSeconds) = " + (endTimeMillis - startTimeMillis));
    }
}