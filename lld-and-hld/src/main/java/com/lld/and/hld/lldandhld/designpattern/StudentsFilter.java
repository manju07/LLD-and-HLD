package com.lld.and.hld.lldandhld.designpattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

/**
 * StudentsFilter
 */
public class StudentsFilter {

    public static void filterStudentsByName(List<Student> students) {
        Map<String, Integer> studentMap = new HashMap<>();
        /**
         * M, N, M, P, N, M
         */
        // students.stream().collect(groupingBy(Student::getName, toSet()));
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "M"));
        students.add(new Student(2, "N"));
        students.add(new Student(3, "M"));
        students.add(new Student(4, "P"));
        students.add(new Student(5, "N"));
        students.add(new Student(6, "M"));
    }

}