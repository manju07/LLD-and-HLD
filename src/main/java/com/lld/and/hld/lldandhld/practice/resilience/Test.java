package com.lld.and.hld.lldandhld.practice.resilience;

import java.util.Base64;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        // System.out.println("Hi Hello");
        byte[] bytes = "Hi Helliio".getBytes();
        String encodeToString = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encodeToString);
        String decodedString = new String(Base64.getDecoder().decode(encodeToString));
        System.out.println(decodedString);
    }
}