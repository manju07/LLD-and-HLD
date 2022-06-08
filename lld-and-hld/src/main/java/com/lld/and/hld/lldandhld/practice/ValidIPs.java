package com.lld.and.hld.lldandhld.practice;

import java.util.ArrayList;
import java.util.List;

public class ValidIPs {

    /**
     * “1230456” -
     * 1.2.3.0
     * 1.2.30.4
     * 1.2.30.45
     * 1.230.4.5
     * 1.230.4.56
     * 1.230.45.6
     * 12.3.
     * 1.2.3.
     */
    public static void findValidIPsUtil(String string, String constructedIP, List<String> resultList, int currentIndex,
            int length, int dotsCount) {
        System.out.println("string="+ string + " constructedIP=" + constructedIP + " resultList=" + resultList + " currentIndex="+ currentIndex + " length=" + length+ " dotsCount=" + dotsCount);
        if ((currentIndex + length) > string.length())
            return;
        String tempString = string.substring(currentIndex, currentIndex + length);
        System.out.println("tempString="+ tempString);
        if (tempString.length() > 1 && tempString.startsWith("0"))
            return;
        int data = Integer.parseInt(tempString);
        if (data < 0 || data > 255)
            return;
        if (dotsCount == 3) {
            constructedIP += (data + "");
            resultList.add(constructedIP);
            System.out.println(resultList);
            return;
        }
        constructedIP += data + ".";
        dotsCount++;
        for (int i = 1; i <= 3; i++) {
            findValidIPsUtil(string, new String(constructedIP), resultList, currentIndex + (tempString.length()), i , dotsCount);
        }
    }

    public static List<String> findAllValidIPs(String string) {
        if (string.length() < 4 || string.length() > 12)
            return null;
        List<String> resultList = new ArrayList<>();
        findValidIPsUtil(string, "", resultList, 0, 1, 0);
        return resultList;
    }

    public static void main(String[] args) {
        List<String> resultList = findAllValidIPs("1230456");
        System.out.println(resultList);
    }
}
