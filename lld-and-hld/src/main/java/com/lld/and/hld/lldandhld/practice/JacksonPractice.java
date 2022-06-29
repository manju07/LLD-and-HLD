package com.lld.and.hld.lldandhld.practice;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Student {
    private int id;
    private String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class JacksonPractice {

    public static void main(String[] args) {
        // Jackson
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = new Student(1, "manju");
            objectMapper.writeValue(
                    new File(
                            "/Users/ma/Manju/LLD-and-HLD/lld-and-hld/src/main/java/com/lld/and/hld/lldandhld/practice/test.json"),
                    student);

            String jsonString = objectMapper.writeValueAsString(student);
            System.out.println("jsonString=" + jsonString);
            System.out.println(
                    "Pretty value \n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}