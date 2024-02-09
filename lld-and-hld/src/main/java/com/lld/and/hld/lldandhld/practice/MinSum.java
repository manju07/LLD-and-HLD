package com.lld.and.hld.lldandhld.practice;

public class MinSum {
    public static int findMinSumPath(int[][] matrix, int n, int m) {
        for (int i = 1; i < m; i++) {
            matrix[0][i] = matrix[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            matrix[i][0] = matrix[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                int min = Math.min(matrix[i][j - 1], matrix[i - 1][j]);
                matrix[i][j] = matrix[i][j] + min;
            }
        }
        return matrix[n - 1][m - 1];
    }

    public static void main(String[] args) {
        
    }
}
