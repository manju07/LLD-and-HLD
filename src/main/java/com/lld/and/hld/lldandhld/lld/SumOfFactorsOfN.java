/**
 * 
 write code to find sum of factors of the input number.
 
 N = 10, {1, 2, 5, 10} = 18
 N = 9,  {1, 3, 9} = 13
 
 1 to N => N%i == 0 , sum += i;
 
 // O(logN)
 1 to sqrt(N) => N%i == 0 , sum += i;
 
 sum += N; 
 */

package com.lld.and.hld.lldandhld.lld;

import java.util.Set;
import java.util.TreeSet;

/**
 * SumOfFactorsOfN
 */
public class SumOfFactorsOfN {

    static int sumOfFactorsOfN(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        Set<Integer> set = new TreeSet<>();

        int sum = 0;

        for (int i = 1; i <= Math.sqrt(n); i++) {

            if (n % i == 0) {

                set.add(i);
                sum += i;

                if ((n / i) != i) {
                    sum += (n / i);
                    set.add(n / i);
                }
            }
        }

        System.out.println(set);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumOfFactorsOfN(0));
        System.out.println(sumOfFactorsOfN(1));
        System.out.println(sumOfFactorsOfN(100));
        System.out.println(sumOfFactorsOfN(9));
        System.out.println(sumOfFactorsOfN(100));
        System.out.println(sumOfFactorsOfN(200));
    }
}
