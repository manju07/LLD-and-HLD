package com.lld.and.hld.lldandhld.practice;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// int arr[] = {1, 4, 5, 8, 3, 7};
// int target = 10;
// 10-7= 3, 3 - 3 = 0;

public class FindSet {
    private static LinkedHashSet<Set<Integer>> setOfSet = new LinkedHashSet<>();

    public static Set<Integer> findSetOfTarget(int[] arr, int target, int index, Set<Integer> set) {
        if (index < 0 && target != 0)
            return null;
        if (target == 0) {
            setOfSet.add(set);
            return set;
        }
        Set<Integer> result1 = null;
        
        if (arr[index] > target) {
            result1 = findSetOfTarget(arr, target, index - 1, set);
        } else if (arr[index] <= target) {

            HashSet<Integer> newSet = new HashSet<>();
            newSet.addAll(set);
            newSet.add(arr[index]);

            Set<Integer> result2 = findSetOfTarget(arr, target - arr[index], index - 1, newSet);

            result1 = findSetOfTarget(arr, target, index - 1, set);

            return result2 != null ? result2 : result1 != null ? result1 : null;
        }
        return result1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 4, 7, 2, 3, 0 };
        System.out.println(findSetOfTarget(arr, 4, arr.length - 1, new HashSet<>()));
        System.out.println(setOfSet);
    }
}
