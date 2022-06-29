package com.lld.and.hld.lldandhld.designpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Test
 * Employee -> Id, name, List<String> skills
 */
class Employee {
    int id;
    String name;
    List<String> skills;

    Employee(int id, String name, List<String> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

}

public class Test {

    public static int filterBySkills(List<Employee> employees, List<String> skills) {
         
        List<Employee> list = employees.stream().filter(employee -> employee.skills.stream().allMatch(skill -> skills.contains(skill) == true)).collect(Collectors.toList());
        System.out.println(list);
                // .max((o1, o2) -> Integer.compare(o1.skills.size(), o2.skills.size()));
            return 0;
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "A", Arrays.asList("Programming", "Sports", "Reading", "Writing")));
        employees.add(new Employee(2, "B", Arrays.asList("Programming", "Writing")));
        employees.add(new Employee(3, "C", Arrays.asList("Programming", "Sports", "Swimming")));
        // employees.add(new Employee(4, "D", Arrays.asList("Programming", "")));
        // employees.add(new Employee(5, "E", Arrays.asList("Programming", "Sports")));
        // employees.add(new Employee(6, "F", Arrays.asList("Programming", "Sports")));
        System.out.println(filterBySkills(employees, Arrays.asList("Programming", "sports")));
        // Programming -> 1, 2, 3
        //
    }

}