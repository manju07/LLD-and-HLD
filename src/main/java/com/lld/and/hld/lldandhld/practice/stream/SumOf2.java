package com.lld.and.hld.lldandhld.practice.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Given array of integer sum =  10, arr[] = [1, 4, 7, 3, 10]

// // approach
// set = [1, 4, 7, ]

/**
 * SumOf2
 */
public class SumOf2 {

    private static void sumOf2IntegersInGivenArray(int arr[], int sum) {
        if (arr == null || arr.length == 0) {
            return;
        }

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            if (set.contains(sum - arr[i])) {
                System.out.println("Sum of 2 numbers = " + (sum - arr[i]) + " " + arr[i]);
            } else {
                set.add(arr[i]);
            }

        }
    }

    private static void sumOf2IntegersInGivenArray_solution_2(int arr[], int sum) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.sort(arr);

        int i = 0, j = arr.length - 1;

        // 1, 3, 4, 7, 10
        while (i < j) {
            int tempSum = arr[i] + arr[j];
            if (tempSum > sum) {
                j--;
            } else if (tempSum < sum) {
                i++;
            } else {
                System.out.println("Sum of 2 numbers (solution2) = " + (arr[i]) + " " + arr[j]);
                break;
            }

        }
    }

    public static void main(String[] args) {
        sumOf2IntegersInGivenArray(new int[] { 1, 4, 7, 3, 10 }, 10);
        sumOf2IntegersInGivenArray_solution_2(new int[] { 1, 4, 7, 3, 10 }, 10);
    }

}
