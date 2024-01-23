package com.lld.and.hld.lldandhld.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GenericClassTest {

    public static void main(String[] args) {
        GenericClass<Number> genericClass = new GenericClass<Number>(1, 2);
        System.out.println(genericClass.sumOfNumbers());

        GenericClass.print(Arrays.asList(1, "test", 1.0, 1.0F));

        GenericClass.printNumbersWithUpperBound(new ArrayList<>(Arrays.asList(1L, 2L, 3L)));

        GenericClass.printNumbersWithLowerBound(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Map<Number, String> map = new HashMap<Number, String>() {
            {
                put(1, "Manju");
                put(2, "Aadu");
            }
        };
        GenericClass.printMapWithUpperBound(map);
        GenericClass.printPrimitiveTypeArray(new Integer[] { 1, 2, 3, 4 });
    }
}
