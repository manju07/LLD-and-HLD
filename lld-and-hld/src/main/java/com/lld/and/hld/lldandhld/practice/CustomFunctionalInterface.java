package com.lld.and.hld.lldandhld.practice;

/**
 * 
 */
@FunctionalInterface
public interface CustomFunctionalInterface {
    Integer test(String str);

    default String test1(Integer data) {
        return data + "";
    }
}