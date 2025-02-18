package com.lld.and.hld.lldandhld.practice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

public class StreamPractice {

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
        // System.out.println(Arrays.stream(new int[] { 1, 2, 3, 1, 4, 5, 2
        // }).distinct().count());
        // boolean result = Arrays.stream(new String[] { "A", "B", "C", "D"
        // }).anyMatch(data -> data.contains("A"));
        // System.out.println(result);
        List<Employee> listOfEmployees = new ArrayList<Employee>();
        listOfEmployees.add(new Employee(1, "Manju", 10000D, "DEV", Arrays.asList("Programming", "Writing")));
        listOfEmployees.add(new Employee(2, "Soda", 12000D, "QA",
                Arrays.asList("Programming", "Coding", "Writing", "Swimming", "E", "F", "G")));
        listOfEmployees.add(new Employee(3, "Netra", 16000D, "QA", Arrays.asList("Swimming", "Writing")));
        listOfEmployees.add(
                new Employee(4, "Vishwa", 20000D, "DEV", Arrays.asList("Programming", "Coding", "A", "B", "C", "D")));
        listOfEmployees.add(new Employee(5, "Shivu", 25000D, "DEV", Arrays.asList("Writing", "Swimming")));

        // List<String> resultList = list.stream().map(employee -> employee.name)
        // .collect(Collectors.toList());
        // System.out.println(resultList);

        // Map<Integer, String> map = list.stream()
        // .collect(Collectors.toMap((employee) -> employee.id, employee ->
        // employee.name));
        // System.out.println(map);

        listOfEmployees.stream().collect(Collectors.toMap((employee) -> employee.id, employee -> employee));

        // Double amount = list.stream().map(employee -> employee.salary).reduce(0D, (a,
        // b) -> a + b);
        // System.out.println("Amount=" + amount);
        // list.stream().collect(Collectors.groupingBy( employee -> employee.dept,
        // employee ->employee.salary));

        unaryOperatorFunction();
        // binaryOperatorFunction();
        // functionOperator();

        // Supplier<String> supplierFI = () -> "data";
        // System.out.println(supplierFI.get());

        // Consumer<String> consumerFI = System.out::println;
        // consumerFI.accept("1234");
        // consumerFI.andThen(consumerFI).accept("1234");

        // Predicate<String> predicateFI = (data) -> data.contains("s");
        // System.out.println(predicateFI.test("soda"));

        // System.out.println("Predicate Result =" + predicateFI.and(data ->
        // data.contains("ss")).test("ss"));

        // System.out.println(listToMap(Arrays.asList("abcmnop", "xyzasd", "pqr"),
        // (data) -> data.length()));
        // System.out.println(listToList(Arrays.asList("abcmnop", "xyzasd", "pqr"),
        // (data) -> data.length()));

        // System.out.println("Sum of Numbers=" + reduce(Arrays.asList(1,2,4,5), 0,
        // (data1, data2) -> data1 + data2));
        // System.out.println("Multiplication of Numbers=" +
        // reduce(Arrays.asList(1,2,4,5), 1, (data1, data2) -> data1 * data2));
        // System.out.println("Sum of Numbers=" +
        // reduceWithIntBinaryOperator(Arrays.asList(1,2,4,5), 0, (data1, data2) ->
        // data1 + data2));

        List<String> list = Arrays.stream(new String[][][] { { { "a", "b" }, { "c",
        "d" }, { "e", "f" } } })
        .flatMap(Stream::of)
        .flatMap(Stream::of)
        // .filter(data -> !data.equals("a"))
        .collect(Collectors.toList());
        System.out.println("FlatMap = " + list);

        // String []strs = new String[] {"a","b","a", };
        // filterByEmployeeSkills(listOfEmployees);
        // groupingByExamples(listOfEmployees);
        // reduceExample();
        typeConversion(listOfEmployees);
        // sortedExamples(listOfEmployees);

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

        System.out.println("sort by keys resultMap=" + unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(data -> data.getKey(), data -> data.getValue(), (oldValue, newValue) -> newValue,
                        () -> new LinkedHashMap<>())));

        System.out.println("sort by values in descending resultMap=" + unsortMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(data -> data.getKey(), data -> data.getValue(), (oldValue, newValue) -> newValue,
                        LinkedHashMap::new)));

        System.out.println("Filter on map = " + unsortMap.entrySet().stream()
                .filter(entrySet -> entrySet.getKey().equals("a") || entrySet.getKey().equals("b"))
                .map(entrySet -> entrySet.getKey())
                .collect(Collectors.joining(" , ")));
    }

    private static void sortedExamples(List<Employee> listOfEmployees) {
        List<Integer> list = listOfEmployees.stream().map(emp -> emp.id).sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(list);

        List<Double> sortBySalary = listOfEmployees.stream().sorted(Comparator.comparingDouble(emp -> emp.salary))
                .map(emp -> emp.salary).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sortBySalary);

        List<Employee> sortBySalaryD = listOfEmployees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println(sortBySalaryD);
    }

    private static void typeConversion(List<Employee> listOfEmployees) {
        System.out.println("\n Method typeConversion \n ");
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1, 2, 3, 4));
        lists.add(Arrays.asList(5, 6, 7, 8));

        int[][] twoDArray = lists.stream().map(list -> list.stream().mapToInt(data -> data).toArray())
                .toArray(int[][]::new);

        for (int i = 0; i < twoDArray.length; i++) {
            for (int j = 0; j < twoDArray[i].length; j++) {
                System.out.print(twoDArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        List<List<Integer>> twoDList = Arrays.stream(twoDArray)
                .map(array -> Arrays.stream(array).map(data -> data).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (int i = 0; i < twoDList.size(); i++) {
            for (int j = 0; j < twoDList.get(i).size(); j++) {
                System.out.print(twoDList.get(i).get(j) + " ");
            }
            System.out.println();
        }

        List<Long> longList = Arrays.stream(new int[] { 1, 2, 3, 4 }).mapToLong(data -> data).boxed()
                .collect(Collectors.toList());
        System.out.print("longArr=");
        for (int i = 0; i < longList.size(); i++) {
            System.out.print(longList.get(i));
            System.out.print(" ");
        }
        int[] empIDs = listOfEmployees.stream().mapToInt(employee -> employee.id).toArray();
        System.out.print("\nEmployee IDs=");
        for (int i = 0; i < empIDs.length; i++) {
            System.out.print(empIDs[i] + " ");
        }

        Integer[] wrapperArray = Arrays.stream(empIDs).boxed().toArray(Integer[]::new);
        int[] oneDArray = Arrays.stream(wrapperArray).mapToInt(data -> data).toArray();
    }

    private static void reduceExample() {
        int sumOf1To10 = IntStream.rangeClosed(1, 10).reduce(0, (a, b) -> a + b);
        System.out.println("sumOf1To10=" + sumOf1To10);
        int resultSum = Stream.iterate(1, n -> n + 1).limit(10).mapToInt(Integer::valueOf).reduce(0, Integer::sum);
        System.out.println("resultSum=" + resultSum);
    }

    private static void groupingByExamples(List<Employee> listOfEmployees) {
        Map<String, Long> resultMap = Stream.of(new String[] { "c", "a", "b", "c", "a", "d", "e" })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("resultMap" + resultMap);

        Set<Long> uniqueSet = resultMap.values().stream().collect(Collectors.toSet());
        System.out.println(uniqueSet);
        Map<String, Long> finalMap = new LinkedHashMap<>();
        resultMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println("finalMap=" + finalMap);

        Map<String, Long> resultMapOnDept = listOfEmployees.stream()
                .collect(Collectors.groupingBy((employee) -> employee.dept, Collectors.counting()));
        System.out.println("resultMapOnDept=" + resultMapOnDept);

        Map<String, Double> collect = listOfEmployees.stream()
                .collect(Collectors.groupingBy((employee) -> employee.dept,
                        Collectors.summingDouble(employee -> employee.salary)));
        System.out.println("collect=" + collect);

        Map<String, List<Integer>> groupByDeptMap = listOfEmployees.stream()
                .collect(Collectors.groupingBy((employee) -> employee.dept,
                        Collectors.mapping(employee -> employee.id, Collectors.toList())));
        System.out.println("groupByDeptMap=" + groupByDeptMap);
    }

    private static void filterByEmployeeSkills(List<Employee> listOfEmployees) {
        List<String> inputSkills = Arrays.asList("Programming", "Coding");
        Optional<Employee> result = listOfEmployees.stream().filter(employee -> inputSkills.stream().allMatch(skill -> {
            for (int i = 0; i < employee.skills.size(); i++)
                if (employee.skills.get(i).equals(skill))
                    return true;
            return false;
        })).collect(Collectors.maxBy((e1, e2) -> e1.skills.size() > e2.skills.size() ? 1 : -1));
        System.out.println(result.get());
    }

    private static void groupingByExamples() {
        Map<String, Long> resultMap = Stream.of(new String[] { "a", "b", "c", "a", "d", "e" })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("resultMap" + resultMap);
        Set<Long> uniqueSet = resultMap.values().stream().collect(Collectors.toSet());
        System.out.println(uniqueSet);
    }

    private static void functionOperator() {
        Function<Integer, String> funct1 = (data) -> data + 100 + "";
        String result = funct1.andThen((data) -> Integer.parseInt(data) + 100).andThen(funct1).apply(100);
        System.out.println(result);
    }

    private static void binaryOperatorFunction() {
        String result;
        BinaryOperator<String> BOF1 = (data1, data2) -> data1 + data2;
        result = BOF1.andThen(data -> data + data).andThen(data -> data + data).andThen(data -> data + data)
                .apply("manju", " ");
        System.out.println("result=" + result);
    }

    private static void unaryOperatorFunction() {
        System.out.println("unaryOperatorFunction");
        UnaryOperator<String> funct1 = ((data) -> data + "123");
        String result = funct1.andThen(data -> data + "456").apply("manju");
        System.out.println(result);
    }

    public static <T, R> Map<T, R> listToMap(List<T> list, Function<T, R> fi) {
        Map<T, R> map = new HashMap<>();
        for (T t : list)
            map.put(t, fi.apply(t));
        return map;
    }

    public static <T, R> List<R> listToList(List<T> list, Function<T, R> fi) {
        List<R> resultList = new ArrayList<>();
        for (T t : list)
            resultList.add(fi.apply(t));
        return resultList;
    }

    public static <T> T reduce(List<T> list, T init, BinaryOperator<T> binaryOperatorFI) {
        T result = init;
        for (T t : list) {
            result = binaryOperatorFI.apply(result, t);
        }
        return result;
    }

    public static int reduceWithIntBinaryOperator(List<Integer> list, int init, IntBinaryOperator intBinaryOperatorFI) {
        for (int t : list) {
            init = intBinaryOperatorFI.applyAsInt(init, t);
        }
        return init;
    }
}