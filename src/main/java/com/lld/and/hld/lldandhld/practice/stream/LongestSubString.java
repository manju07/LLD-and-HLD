package com.lld.and.hld.lldandhld.practice.stream;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 * abcdef
 * abcdefalkc
 * abcxyzac123456


fromIndex = 0;
currentIndex = 0 to N;

hm = {a, 0}, {b, 1} ... 

RepeatedCharacter
	calculating length 
	fromIndex to previous position of RepeatedCharacter+1;
	change postion in hm - {a, 6}, 
 */

public class LongestSubString {

    private static int longestSubStringWithoutRepeatedCharacter(String str) {
        int maxLongestSubStringLength = 0;

        if (str == null || str.isEmpty()) {
            return maxLongestSubStringLength;
        }

        Map<Character, Integer> map = new HashMap<>();

        int fromIndex = 0, currentIndex = 0;

        for (; currentIndex < str.length(); currentIndex++) {
            Character c = str.charAt(currentIndex);

            if (!map.containsKey(c)) {
                map.put(c, currentIndex);

            } else {

                int length = (currentIndex - fromIndex) + 1;

                if (length > maxLongestSubStringLength) {
                    System.out.println("Substr1=" + str.substring(fromIndex, currentIndex));
                    maxLongestSubStringLength = length;
                }

                int newFromIndex = map.get(c) + 1;
                if (newFromIndex > fromIndex) {
                    fromIndex = newFromIndex;
                }
                map.put(c, currentIndex);
            }
        }

        int length = (currentIndex - fromIndex);
        
        if (length > maxLongestSubStringLength) {
            System.out.println("Substr2=" + str.substring(fromIndex));
            maxLongestSubStringLength = length;
        } 

        return maxLongestSubStringLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubStringWithoutRepeatedCharacter("abcdecghja12345"));
        // "abcdecghja12345"
    }
}
