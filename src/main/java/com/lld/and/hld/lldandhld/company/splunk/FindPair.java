package com.lld.and.hld.lldandhld.company.splunk;

/*
We have a catalog of song titles (and their lengths) that we play at a local radio station.  We have been asked to play two of those songs in a row, and they must add up to exactly seven minutes long.  

Given a list of songs and their durations, write a function that returns the names of any two distinct songs that add up to exactly seven minutes.  If there is no such pair, return an empty collection. 

Example:
song_times_1 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Good Times Bad Times", "2:48"), ("Hot Dog", "3:19"),
    ("The Crunge", "3:18"), ("Achilles Last Stand", "10:26"),
    ("Black Dog", "4:55")
]
find_pair(song_times_1) => ["Rock and Roll", "Hot Dog"] (3:41 + 3:19 = 7:00)

Additional Input:
song_times_2 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Good Times Bad Times", "2:48"), ("Black Dog", "4:55"),
    ("The Crunge", "3:18"), ("Achilles Last Stand", "10:26"),
    ("The Ocean", "4:31"), ("Hot Dog", "3:19"),
]
song_times_3 = [
    ("Stairway to Heaven", "8:05"), ("Immigrant Song", "2:27"),
    ("Rock and Roll", "3:41"), ("Communication Breakdown", "2:29"),
    ("Hey Hey What Can I Do", "4:00"), ("Poor Tom", "3:00"),
    ("Black Dog", "4:55")
]
song_times_4 = [
    ("Hey Hey What Can I Do", "4:00"), ("Rock and Roll", "3:41"),
    ("Communication Breakdown", "2:29"), ("Going to California", "3:30"),
    ("On The Run", "3:50"), ("The Wrestler", "3:50"), 
    ("Black Mountain Side", "2:11"), ("Brown Eagle", "2:20")
]
song_times_5 = [("Celebration Day", "3:30"), ("Going to California", "3:30")]
song_times_6 = [
  ("Rock and Roll", "3:41"), ("If I lived here", "3:59"),
  ("Day and night", "5:03"), ("Tempo song", "1:57")
]


Complexity Variable:
n = number of song/time pairs

All Test Cases - snake_case:
find_pair(song_times_1) => ["Rock and Roll", "Hot Dog"]
find_pair(song_times_2) => ["Rock and Roll", "Hot Dog"] or ["Communication Breakdown", "The Ocean"]
find_pair(song_times_3) => ["Hey Hey What Can I Do", "Poor Tom"]
find_pair(song_times_4) => []
find_pair(song_times_5) => ["Celebration Day", "Going to California"]
find_pair(song_times_6) => ["Day and night", "Tempo song"]

All Test Cases - camelCase:
findPair(songTimes1) => ["Rock and Roll", "Hot Dog"]
findPair(songTimes2) => ["Rock and Roll", "Hot Dog"] or ["Communication Breakdown", "The Ocean"]
findPair(songTimes3) => ["Hey Hey What Can I Do", "Poor Tom"]
findPair(songTimes4) => []
findPair(songTimes5) => ["Celebration Day", "Going to California"]
findPair(songTimes6) => ["Day and night", "Tempo song"]
*/
import java.io.*;
import java.util.*;

public class FindPair {
  static String[] find_pair(String[][] songTimes) {
    String result[] = new String[2];
    Map<Float, String> hashMap = new HashMap<>();

    for (String[] songTime : songTimes) {

      String name = songTime[0];
      String[] timeStringArr = songTime[1].split(":");

      Float currentTime = Float.valueOf(songTime[1].replace(":", "."));
      Float minutes = Float.valueOf(timeStringArr[0]);
      Float seconds = Float.valueOf(timeStringArr[1]);
      
      Float remainRequiredMinutes = 7 - minutes;
      Float remainRequiredSeconds = 0.0f;

      if (seconds > 0) {
        remainRequiredMinutes -= 1;
        remainRequiredSeconds = 60 - seconds;
      }

      Float requiredTime = Float.valueOf(remainRequiredMinutes + (remainRequiredSeconds / 100));

      if (hashMap.containsKey(requiredTime)) {

        result[0] = hashMap.get(requiredTime);
        result[1] = name;
        return result;

      }

      hashMap.put(currentTime, name);

    }
    return result;
  }

  public static void main(String[] argv) {
    String[][] songTimes1 = {
        { "Stairway to Heaven", "8:05" }, { "Immigrant Song", "2:27" },
        { "Rock and Roll", "3:41" }, { "Communication Breakdown", "2:29" },
        { "Good Times Bad Times", "2:48" }, { "Hot Dog", "3:19" },
        { "The Crunge", "3:18" }, { "Achilles Last Stand", "10:26" },
        { "Black Dog", "4:55" }
    };
    String[] result = find_pair(songTimes1);
    System.out.println(result[0] + ", " + result[1]);

    String[][] songTimes2 = {
        { "Stairway to Heaven", "8:05" }, { "Immigrant Song", "2:27" },
        { "Rock and Roll", "3:41" }, { "Communication Breakdown", "2:29" },
        { "Good Times Bad Times", "2:48" }, { "Black Dog", "4:55" },
        { "The Crunge", "3:18" }, { "Achilles Last Stand", "10:26" },
        { "The Ocean", "4:31" }, { "Hot Dog", "3:19" }
    };

    result = find_pair(songTimes2);
    System.out.println("\n" + result[0] + ", " + result[1]);

    String[][] songTimes3 = {
        { "Stairway to Heaven", "8:05" }, { "Immigrant Song", "2:27" },
        { "Rock and Roll", "3:41" }, { "Communication Breakdown", "2:29" },
        { "Hey Hey What Can I Do", "4:00" }, { "Poor Tom", "3:00" },
        { "Black Dog", "4:55" }
    };
    result = find_pair(songTimes3);
    System.out.println("\n" + result[0] + ", " + result[1]);

    String[][] songTimes4 = {
        { "Hey Hey What Can I Do", "4:00" }, { "Rock and Roll", "3:41" },
        { "Communication Breakdown", "2:29" }, { "Going to California", "3:30" },
        { "On The Run", "3:50" }, { "The Wrestler", "3:50" },
        { "Black Mountain Side", "2:11" }, { "Brown Eagle", "2:20" }
    };
    result = find_pair(songTimes4);
    System.out.println("\n" + result[0] + ", " + result[1]);

    String[][] songTimes5 = {
        { "Celebration Day", "3:30" }, { "Going to California", "3:30" }
    };
    result = find_pair(songTimes5);
    System.out.println("\n" + result[0] + ", " + result[1]);

    String[][] songTimes6 = {
        { "Rock and Roll", "3:41" }, { "If I lived here", "3:59" },
        { "Day and night", "5:03" }, { "Tempo song", "1:57" }
    };
    result = find_pair(songTimes6);
    System.out.println("\n" + result[0] + ", " + result[1]);
  }
}
