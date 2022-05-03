package com.lld.and.hld.lldandhld.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPractice {

    public static void main(String[] args) {
        oneDArrayConvertFromList();

        twoDArrayConvertFromListOfList();

        convertPrimitive2DArrrayToList();
    }

    private static void convertPrimitive2DArrrayToList() {
        List<int[]> tempData = new ArrayList<>();
        tempData.add(new int[] { 1, 2, 3, 4 });

        int[][] twoDArray = { { 2, 3, 4, 5 }, { 1, 2, 3, 4 } };
        List<List<Integer>> resultList = Arrays.stream(twoDArray)
                .map(list -> Arrays.stream(list).mapToObj(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(resultList);
    }

    private static void twoDArrayConvertFromListOfList() {
        List<List<Integer>> listOfList = Arrays.asList(new ArrayList<Integer>(Arrays.asList(1, 2, 4)),
                new ArrayList<Integer>(Arrays.asList(5, 7, 9)));

        int[][] twoDarray = listOfList.parallelStream().map(
                list -> list.stream().mapToInt(data -> data).toArray()).toArray(int[][]::new);
        System.out.println("2D array");
        printTwoDArray(twoDarray);
    }

    private static void printTwoDArray(int[][] twoDarray) {
        for (int i = 0; i < twoDarray.length; i++) {
            for (int j = 0; j < twoDarray[i].length; j++) {
                System.out.print(twoDarray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void oneDArrayConvertFromList() {
        int[] array = Arrays.asList(1, 2, 3, 4).stream().mapToInt(data -> data).toArray();
        System.out.println("1D array");
        printOneDArray(array);
    }

    private static void printOneDArray(int[] array) {
        for (int data : array) {
            System.out.println(data);
        }
    }
}