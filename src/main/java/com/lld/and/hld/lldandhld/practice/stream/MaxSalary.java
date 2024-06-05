package com.lld.and.hld.lldandhld.practice.stream;
// name, salary

// a, 1000
// b, 1500
// c, 900
// d, 1200

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * MaxSalary
 */
public class MaxSalary {

    @Data
    @AllArgsConstructor
    @ToString
    static class Person {
        private String name;
        private Double salary;
    }

    @SuppressWarnings("unchecked")
    private static List<Person> findMaxSalaryPersons(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<Person> resultList = new ArrayList<>();

        Double maxSalary = Double.MIN_VALUE;

        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getSalary() > maxSalary) {
                maxSalary = person.getSalary();
                resultList.clear();
                resultList.add(person);
            } else if (person.getSalary().equals(maxSalary)) {
                resultList.add(person);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("a", 1000.0), new Person("b", 1500.0), new Person("c", 900.0),
                new Person("d", 1500.0));
        System.out.println(findMaxSalaryPersons(persons));
    }
}
