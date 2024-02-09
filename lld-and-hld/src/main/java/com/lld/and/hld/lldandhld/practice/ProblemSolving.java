package com.lld.and.hld.lldandhld.practice;

/**
 * it contains only 0's and 1's
 * 
 * 
 * and the matrix is sorted row wise
 * 
 * 
 * return the row with maximum 1's
 * 
 * 
 * example -
 * 
 * 
 * 0 0 1 1 1 1
 * 0 0 0 0 1 1
 * 0 1 1 1 1 1
 * 1 1 1 1 1 1
 * 0 0 0 0 0 0
 * ans = 3
 * 
 * 
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 */
public class ProblemSolving {

    public static int findMaxOnceInARow(int[][] matrix, int n, int m) {
        int maxOnceCount = 0, lastOccuredOncePosition = m - 1, resultRow = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = lastOccuredOncePosition; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    int count = m - j - 1;
                    if (count == m)
                        return i;
                    if (count > maxOnceCount) {
                        maxOnceCount = count;
                        lastOccuredOncePosition = j + 1;
                        resultRow = i;
                    }
                    break;
                }
            }
        }
        return resultRow;
    }

    public static void main(String[] args) {
        int matrix[][] = {
                { 0, 0, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0 } };
        System.out.println("result=" + findMaxOnceInARow(matrix, matrix.length, matrix[0].length));
    }

}
