package com.lld.and.hld.lldandhld.practice;

/**
 * 
 */
public interface FunctionalInterface {
    Integer test(String str);

    default String test1(Integer data) {
        return data + "";
    }
}