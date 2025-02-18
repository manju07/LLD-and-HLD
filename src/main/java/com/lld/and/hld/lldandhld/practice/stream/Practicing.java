package com.lld.and.hld.lldandhld.practice.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Practicing {
    
    public static void main(String[] args) {
        
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Set<Integer> set = list.stream().collect(Collectors.toSet());
        
    }
}
