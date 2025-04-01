package com.lld.and.hld.lldandhld.practice;


import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class Employee {
    int id;
    String name;
    String dept;
    BigDecimal salary;
}

public class Challenge {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.addAll(Arrays.asList(new Employee(1, "Manju", "Developer", new BigDecimal(100000)), new Employee(2, "Shivu", "Developer", new BigDecimal(300000)), new Employee(3, "Sss", "Tester", new BigDecimal(300001)), new Employee(4, "xyz", "Tester", new BigDecimal(15)), new Employee(5, "mno", "Developer", new BigDecimal(90))));

        sortBySalary(employees);
        sortBySalary_Idea2(employees);
        convertListToArrayAndViceVersa(employees);
        empIdSet(employees);
        groupByEmpDept(employees);

//        optionalValidateData(employees);
//        optionalCreationWays();

//        List<List<Integer>> listOfList = new ArrayList<>();
//        listOfList.addAll(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6)));
//
//        flattenList(listOfList);
//
//        flattenToPrimitiveInt(listOfList);
//
//        listOfListsTo2DArr(listOfList);

//        mapMethodsTest();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b) -> a - b);

        for (int data: new int[] {56, 43, 2, 9, 89, 3, 5, 10, 8}) {
            priorityQueue.add(data);
        }

        System.out.println("priorityQueue="+priorityQueue);

        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> newPriorityQueue = new PriorityQueue<>(priorityQueue);
        for (int i = 0; i < 5; i++) {
            list.add(priorityQueue.poll());
        }


        System.out.println("list="+list);
        System.out.println("newPriorityQueue="+ newPriorityQueue);
    }

    private static void mapMethodsTest() {
        Map<Character, Integer> map = new HashMap<>();
        String data = "aaabbacdopaklmmaanooppprrn";
        for (int i = 0; i < data.length(); i++) {
            Character key = data.charAt(i);
            map.computeIfPresent(key, (k, v) -> v + 1);
            map.putIfAbsent(key, 1);
        }
        System.out.println("each character appears map =" +map);

        val treeMap = new TreeMap<Character, Integer>((k1, k2) -> map.get(k1).compareTo(map.get(k2)));
        treeMap.putAll(map);
        System.out.println("each character appears with treemap sort in asc(keep only one element as unique) =" + treeMap);

        LinkedHashMap<Character, Integer> linkedHashMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (oldValue, newValue) -> newValue, LinkedHashMap::new));

        System.out.println("each character appears with LinkedHashMap sort in desc =" + linkedHashMap);
    }

    private static void empIdSet(List<Employee> employees) {
        Set<Integer> uniqueEmployeeIds = Optional.of(employees).orElseGet(ArrayList::new).stream().map(employee -> employee.id).collect(Collectors.toSet());
        System.out.println(uniqueEmployeeIds);
    }

    private static void groupByEmpDept(List<Employee> employees) {
        HashMap<String, Set<Integer>> mapByDept = employees.stream().collect(Collectors.groupingBy(employee -> employee.dept, HashMap::new, Collectors.mapping(employee -> employee.id, Collectors.toSet())));
        System.out.println(mapByDept);
    }

    private static void listOfListsTo2DArr(List<List<Integer>> listOfList) {
        int[][] twoDArray = listOfList.stream().map(list -> list.stream().mapToInt(data -> data).toArray()).toArray(int[][]::new);
        System.out.println("twoDArray=");
        for (int[] arr : twoDArray) {
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void flattenToPrimitiveInt(List<List<Integer>> listOfList) {
        int[] intArray = listOfList.stream().flatMap(Collection::stream).mapToInt(data -> data).toArray();
        System.out.println("intArray=" + Arrays.toString(intArray));
    }

    private static void flattenList(List<List<Integer>> listOfList) {
        List<Integer> flattenData = listOfList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("flattenData=" + flattenData);
    }

    private static void convertListToArrayAndViceVersa(List<Employee> employees) {
        Employee[] employeesArray = employees.toArray(new Employee[0]);
        System.out.println("Employee employeesArray =" + Arrays.toString(employeesArray));

        List<Employee> convertedEmployeeList = Arrays.stream(employeesArray).collect(Collectors.toList());
        System.out.println("convertedEmployeeList=" + convertedEmployeeList);
    }

    private static void sortBySalary(List<Employee> employees) {
        Map<Integer, BigDecimal> map = employees.stream().collect(Collectors.toMap(employee -> employee.id, employee -> employee.salary));
        TreeMap<Integer, BigDecimal> treeMap = new TreeMap<>((a, b) -> {
            return map.get(a).compareTo(map.get(b));
        });
        treeMap.putAll(map);
        System.out.println("asc treeMap=" + treeMap);
        TreeMap<Object, Object> treeMapDesc = new TreeMap<>((a, b) -> {
            return map.get(b).compareTo(map.get(a));
        });
        treeMapDesc.putAll(treeMap);
        System.out.println("desc treeMapDesc=" + treeMapDesc);
    }

    private static void sortBySalary_Idea2(List<Employee> employees) {
        LinkedHashMap<Integer, BigDecimal> linkedHashMap = employees.stream().collect(Collectors.toMap(employee -> employee.id, employee -> employee.salary)).entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldData, newData) -> newData, LinkedHashMap::new));

        System.out.println("Asc linkedHashMap=" + linkedHashMap);

        LinkedHashMap<Integer, BigDecimal> linkedHashMapSalaryInDesc = employees.stream().collect(Collectors.toMap(employee -> employee.id, employee -> employee.salary)).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldData, newData) -> newData, LinkedHashMap::new));
        System.out.println("Desc linkedHashMapSalaryInDesc=" + linkedHashMapSalaryInDesc);
    }

    private static void optionalValidateData(List<Employee> employees) {
        Optional<List<Employee>> listOfEmployees = Optional.of(employees);
        if (listOfEmployees.isPresent()) {
            System.out.println("data present");
            List<Employee> employees1 = listOfEmployees.get();
            Set<Integer> ids = employees1.stream().map(Employee::getId).collect(Collectors.toSet());
            System.out.println(ids);
        } else {
            System.out.println("Data is not present");
        }
        Set<Employee> dataIsNull = Optional.ofNullable(employees).orElseThrow(() -> new RuntimeException("data is null")).stream().collect(Collectors.toSet());
        System.out.println("dataIsNull" + dataIsNull);

        Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("data is null"));
    }

    private static void optionalCreationWays() {
        try {
            Optional<Object> nullOptional = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("Handling exception - e" + e);
        }

        try {
            Optional<Object> nullableOptional = Optional.ofNullable(null);
            System.out.println("Not thrown exception with ofNullable");
        } catch (NullPointerException e) {
            System.out.println("Handling exception - e" + e);
        }

        try {
            Optional<Object> emptyOptional = Optional.empty();
            System.out.println("Created emptyOptional");
        } catch (NullPointerException e) {
            System.out.println("Handling exception - e" + e);
        }
    }
}
