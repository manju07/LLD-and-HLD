package com.lld.and.hld.lldandhld.practice;

import java.util.HashMap;
import java.util.Map;

public class Knapsack {

    static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    private static int maxProfit(int n, int[] profit, int[] weight, int W) {
        if (n < 0 || W == 0) return 0;

        if (map.containsKey(n) && map.get(n).containsKey(W)) {
            System.out.println("n=" + n + " W=" + W + " result=" + map.get(n).get(W));
            return map.get(n).get(W);
        }

        if (weight[n] > W)
            return maxProfit(n - 1, profit, weight, W);

        int result = Math.max(profit[n] + maxProfit(n - 1, profit, weight, W - weight[n]),
                maxProfit(n - 1, profit, weight, W));

        if (map.containsKey(n)) {
            map.get(n).put(W, result);
        } else {
            Map<Integer, Integer> weightMap = new HashMap<>();
            weightMap.put(W, result);
            map.put(n, weightMap);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] profit = {4, 3, 3, 8, 1, 2, 3};
        int[] weight = {2, 1, 2, 3, 3, 1, 4};
        System.out.println(maxProfit(profit.length - 1, profit, weight, 6));
    }
}
