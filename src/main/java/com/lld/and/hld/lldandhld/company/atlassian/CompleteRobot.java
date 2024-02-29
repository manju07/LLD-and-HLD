package com.lld.and.hld.lldandhld.company.atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/*
We have a bin of robot parts in a factory. Each part goes to a robot with a specific, unique name. Each part will be described by a string, with the name of the robot and the part name separated by an underscore, like "Rosie_arm".

All robots are made of the same types of parts, and we have a string of all of the parts required to form a complete robot. Given a list of available parts, return the collection of robot names for which we can build at least one complete robot.

Sample Input:

all_parts = [
    "Rosie_claw",
    "Rosie_sensors",
    "Dustie_case", 
    "Optimus_sensors",
    "Rust_sensors",
    "Rosie_case",
    "Rust_case",
    "Optimus_speaker",
    "Rosie_wheels",
    "Rosie_speaker",
    "Dustie_case",
    "Dustie_arms",
    "Rust_claw",
    "Dustie_case",
    "Dustie_speaker",
    "Optimus_case",
    "Optimus_wheels",
    "Rust_legs",
    "Optimus_sensors" ]

required_parts_1 = "sensors,case,speaker,wheels"
required_parts_2 = "sensors,case,speaker,wheels,claw"
required_parts_3 = "sensors,case,screws"

Expected Output (output can be in any order):

get_robots(all_parts, required_parts_1) => ["Optimus", "Rosie"]
get_robots(all_parts, required_parts_2) => ["Rosie"]
get_robots(all_parts, required_parts_3) => []

N: Number of elements in `all_parts`
P: Number of elements in `required_parts`
*/
public class CompleteRobot {

    static List<String> get_robots(String[] allParts, String requiredParts) {
        if (allParts == null)
            return null;

        String[] requiredPartsArr = requiredParts.split(",");
        Map<String, HashSet<String>> robotDataMap = new HashMap<>();

        for (int i = 0; i < allParts.length; i++) {
            String[] robotData = allParts[i].split("_");

            HashSet<String> parts = new HashSet<>();

            if (robotDataMap.containsKey(robotData[0])) {

                parts = robotDataMap.get(robotData[0]);
                parts.add(robotData[1]);

            } else {

                parts.add(robotData[1]);
                robotDataMap.put(robotData[0], parts);
            }

        }

        List<String> completeRobotList = new ArrayList<String>();

        for (Map.Entry<String, HashSet<String>> data : robotDataMap.entrySet()) {

            boolean robotHaveAllParts = true;
            HashSet<String> parts = data.getValue();

            for (int i = 0; i < requiredPartsArr.length; i++) {

                if (!parts.contains(requiredPartsArr[i])) {
                    robotHaveAllParts = false;
                    break;
                }
            }

            if (robotHaveAllParts) {
                completeRobotList.add(data.getKey());
            }
        }
        return completeRobotList;
    }

    public static void main(String[] argv) {
        String required_parts_1 = "sensors,case,speaker,wheels";
        String required_parts_2 = "sensors,case,speaker,wheels,claw";
        String required_parts_3 = "sensors,case,screws";

        String[] all_parts = {
                "Rosie_claw",
                "Rosie_sensors",
                "Dustie_case",
                "Optimus_sensors",
                "Rust_sensors",
                "Rosie_case",
                "Rust_case",
                "Optimus_speaker",
                "Rosie_wheels",
                "Rosie_speaker",
                "Dustie_case",
                "Dustie_arms",
                "Rust_claw",
                "Dustie_case",
                "Dustie_speaker",
                "Optimus_case",
                "Optimus_wheels",
                "Rust_legs",
                "Optimus_sensors" };

        System.out.println(get_robots(all_parts, required_parts_1));
        System.out.println(get_robots(all_parts, required_parts_2));
        System.out.println(get_robots(all_parts, required_parts_3));
        System.out.println(get_robots(all_parts, ""));
    }
}
