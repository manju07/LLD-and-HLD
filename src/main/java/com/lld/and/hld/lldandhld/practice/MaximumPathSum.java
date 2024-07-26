// Given two sorted arrays A and B of size M and N respectively. Each array may have some elements in common with the other array. Find the maximum sum of a path from the beginning of any array to the end of any of the two arrays. We can switch from one array to another array only at the common elements which are present at the same index.
// Note: Only one repeated value is considered in the valid path sum.
// Example 1:
// Input:
// M = 5, N = 4
// A[] = {2,3,7,10,12}
// B[] = {1,5,7,8}
// output: 35
// explainataion: - 1, 5, 7, 10, 12 = 35

// Paths: 
//     - 2, 3, 7, 10, 12
//     - 2, 3, 7, 8
//     - 1, 5, 7, 8
//     - 1, 5, 7, 10, 12 = 35

package com.lld.and.hld.lldandhld.practice;


/**
 * MaximumPathSum
 */
public class MaximumPathSum {

    // M = 5, N = 4
    // A[] = {2,3,7,10,12}
    // B[] = {1,5,7,8}
    // output: 35
    // explainataion: - 1, 5, 7, 10, 12 = 35

    // Paths:
    // - 2, 3, 7, 10, 12
    // - 2, 3, 7, 8
    // - 1, 5, 7, 8
    // - 1, 5, 7, 10, 12 = 35

    private static int maximumPathSumMethod(int[] A, int[] B, int M, int N, int index, int sum, boolean usingA) {

        if (A == null || B == null || M == 0 || N == 0 || (index >= M && index >= N)) {
            return sum;
        }
        int resultA = 0;
        int resultB = 0;

        if (index < M && index < N) {
            int switchResult = 0;

            if (A[index] == B[index]) {

                if (usingA) {
                    switchResult = maximumPathSumMethod(A, B, M, N, index + 1, sum + A[index], !usingA);
                } else {
                    switchResult = maximumPathSumMethod(A, B, M, N, index + 1, sum + B[index], !usingA);
                }
            }

            if (usingA) {
                resultA = maximumPathSumMethod(A, B, M, N, index + 1, sum + A[index], usingA);
            } else {
                resultB = maximumPathSumMethod(A, B, M, N, index + 1, sum + B[index], usingA);
            }
            resultA = switchResult > resultA ? switchResult : resultA;

        } else if (index < M) {
            resultA = maximumPathSumMethod(A, B, M, N, index + 1, sum + A[index], usingA);
        } else if (index < N) {
            resultB = maximumPathSumMethod(A, B, M, N, index + 1, sum + B[index], usingA);
        }
        return resultA > resultB ? resultA : resultB;
    }

    public static void main(String[] args) {
        int A[] = { 1, 3, 5, 5, 8 };
        int B[] = { 2, 3, 3, 5, 7 };

        int M = A.length, N = B.length;
        int aSum = maximumPathSumMethod(A, B, M, N, 0, 0, true);
        int bSum = maximumPathSumMethod(A, B, M, N, 0, 0, false);
        if (aSum > bSum) {
            System.out.println("result=" + aSum);
        } else {
            System.out.println("result=" + bSum);
        }
    }
}
