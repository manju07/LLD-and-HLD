package com.lld.and.hld.lldandhld.practice;

import java.util.Arrays;

/**
 * Longest palindromic subsequence
 */
public class LPS {

    private static int longestPalindromeSubsequence(int l, int h, String data, int[][] mem) {
        if (l == h)
            return 1;
        if (mem[l][h] != -1)
            return mem[l][h];

        if (data.charAt(l) == data.charAt(h))
            return 2 + longestPalindromeSubsequence(l + 1, h - 1, data, mem);

        int result = Math.max(longestPalindromeSubsequence(l + 1, h, data, mem),
                longestPalindromeSubsequence(l, h - 1, data, mem));
        return mem[l][h] = result;
    }

    public static void main(String[] args) {
        String data = "banana";
        int[][] mem = new int[data.length()][data.length()];
        for (int arr[] : mem) {
            Arrays.fill(arr, -1);
        }

        System.out.println(longestPalindromeSubsequence(0, data.length() - 1, data, mem));
    }
}
