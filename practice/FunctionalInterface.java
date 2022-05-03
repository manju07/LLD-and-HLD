package com.spring.core;

/**
 * 
 */
public interface FunctionalInterface {
    Integer test(String str);

    default String test1(Integer data) {
        return data + "";
    }
}