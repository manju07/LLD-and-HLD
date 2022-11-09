package com.lld.and.hld.lldandhld.practice;

import java.util.Optional;

public class OptionalPractice {
    public static void main(String[] args) {
        Optional<String> optionalData = Optional.of("data");
        Optional<String> emptyOptional = Optional.empty();

        Optional<String> newOptional1 = optionalData.ofNullable("data");
        Optional<String> newOptional2 = optionalData.ofNullable("data1");

        if (optionalData.isPresent()) {
            System.out.println("Data=" + optionalData.get());
        } else {
            System.out.println("no data present");
        }

        System.out.println(newOptional1.orElse(null));
        newOptional1.ifPresent(data -> {
            System.out.println("data=" + data);
        });

        System.out.println(optionalData.orElseGet(() -> "null"));
        System.out.println(emptyOptional.orElseGet(() -> null));

        Optional<Optional<Optional<String>>> multiOptional = Optional.of(Optional.of(Optional.of("manju")));
        System.out.println(multiOptional.flatMap(optional -> optional.flatMap(optional2 -> optional2)).orElse(null));
        System.out.println("flat map result=" + optionalData.map(String::toUpperCase).get());

        System.out.println("optional map = " + optionalData.map(String::toUpperCase).orElse("null"));
        System.out.println("filter operation result = " + optionalData.filter(data -> data.equals("data")).orElse(null));
    }
}