/**
* Given an array arr[] of n integers, construct a Product Array prod[] (of the same size) such that prod[i]
* is equal to the product of all the elements of arr[] except arr[i]
*

*
* Input: arr[]  = {10, 3, 5, 6, 2}
* Output: prod[]  = {180, 600, 360, 300, 900}

Max = 10 * 3 * 5 * 6 * 2 =  1800

10 -> 3 * 5 * 6 * 2 = 1800/10 = 180
3 -> 10 * 5 * 6 * 2 = 1800/3 = 600



test cases:
    - null => 
    - {} => {}
    - {-1, -2, -3}
    - {10, 3, 5, 6, 2} => {180, 600, 360, 300, 900}
    - large data set
*/
package com.lld.and.hld.lldandhld.company.walmart.IJP;

/**
 * ConstructProdArr
 */
public class ConstructProdArr {

    public static long[] constructProdArr(int arr[]) {
        if (arr == null || arr.length == 0) 
            return new long[] {};
        
        long productOfAllElements = 1;
        for (int i = 0; i < arr.length; i++) {
            productOfAllElements *= arr[i];
        }

        long[] prodArr = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            prodArr[i] = productOfAllElements / arr[i];
        }
        return prodArr;
    }


    public static void main(String[] args) {
        // long[] constructedProdArr = constructProdArr(null);
        // printArray(constructedProdArr);

        long[] constructedProdArr = constructProdArr(new int[] { });
        printArray(constructedProdArr);

        constructedProdArr = constructProdArr(new int[] { 10, 3, 5, 6, 2 });
        printArray(constructedProdArr);

        constructedProdArr = constructProdArr(new int[] { -1, -2, -3 });
        printArray(constructedProdArr);

        constructedProdArr = constructProdArr(new int[] { 10, 3, 0, 6, 2 });
        printArray(constructedProdArr);

    }

    private static void printArray(long[] constructedProdArr) {
        System.out.println();
        for (int i = 0; i < constructedProdArr.length; i++) {
            System.out.print(constructedProdArr[i] + " ");
        }
    }
}
