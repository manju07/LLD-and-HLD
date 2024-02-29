// You are given an array of N integers,

//  Return the of count of distinct numbers in all windows of size B

// NOTE: if B > N, return an empty array.

// Input 1:

//  A = [1, 2, 1, 3, 4, 3]
//  B = 3

// output:[2, 3, 3, 2]

// Input 2:
//  A = [1, 1, 2, 2]
//  B = 1
//  output: [1,1,1,1]

package com.lld.and.hld.lldandhld.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * FindDistinctNumbers
 */
public class FindDistinctNumbers {

    public static List<Integer> findDistinctNumbersInEachWindow(int[] arr, int B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        if (B >= arr.length)
            return Arrays.asList();
        for (int i = 0; i < B; i++) {
            if (map.containsKey(arr[i])) {
                int count = map.get(arr[i]);
                map.put(arr[i], count + 1);
            } else
                map.put(arr[i], 1);
        }
        resultList.add(map.size());
        for (int i = B; i < arr.length; i++) {
            int count = map.get(arr[i - B]);
            if (count == 1)
                map.remove(arr[i - B]);
            else
                map.put(arr[i - B], count - 1);

            if (map.containsKey(arr[i])) {
                count = map.get(arr[i]);
                map.put(arr[i], count + 1);
            } else
                map.put(arr[i], 1);
            resultList.add(map.size());
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(findDistinctNumbersInEachWindow(new int[] { 1, 2, 1, 3, 4, 3 }, 3));
        System.out.println(findDistinctNumbersInEachWindow(new int[] { 1, 1, 2, 2 }, 1));
    }
}