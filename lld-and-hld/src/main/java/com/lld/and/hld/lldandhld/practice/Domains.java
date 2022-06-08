package com.lld.and.hld.lldandhld.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * Input
 * counts =
 * [ "900,google.com",
 * "60,mail.yahoo.com",
 * "10,mobile.sports.yahoo.com",
 * "40,sports.yahoo.com",
 * "300,yahoo.com",
 * "10,stackoverflow.com",
 * "20,overflow.com",
 * "5,com.com",
 * "2,en.wikipedia.org",
 * "1,m.wikipedia.org",
 * "1,mobile.sports",
 * "1,google.co.uk"]
 * 
 * Output
 * com: 1345
 * google.com: 900
 * stackoverflow.com: 10
 * overflow.com: 20
 * yahoo.com: 410
 * mail.yahoo.com: 60
 * mobile.sports.yahoo.com: 10
 * sports.yahoo.com: 50
 * com.com: 5
 * org: 3
 * 
 * 
 * google.com -> [com, google.com]
 * 60, mail.yahoo.com -> [mail.yahoo.com, yahoo.com, com]
 */
public class Domains {

    public static Map<String, Integer> findAllHitsInEachDomain(List<String> list) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String data[] = list.get(i).split(",");
            int count = Integer.parseInt(data[0]);
            String domain = data[1];

            if (!resultMap.containsKey(domain))
                resultMap.put(domain, count);
            else {
                int tempCount = resultMap.get(domain);
                resultMap.put(domain, tempCount + count);
            }

            while (true) {
                int index = domain.indexOf(".");

                if (index == -1)
                    break;

                domain = domain.substring(index + 1); // sports.yahoo.com
                if (!resultMap.containsKey(domain))
                    resultMap.put(domain, count);
                else {
                    int tempCount = resultMap.get(domain);
                    resultMap.put(domain, tempCount + count);
                }
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("900,google.com", "60,mail.yahoo.com",
                "10,mobile.sports.yahoo.com",
                "40,sports.yahoo.com",
                "300,yahoo.com",
                "10,stackoverflow.com",
                "20,overflow.com",
                "5,com.com",
                "2,en.wikipedia.org",
                "1,m.wikipedia.org",
                "1,mobile.sports",
                "1,google.co.uk");
        Map<String, Integer> resultMap = findAllHitsInEachDomain(list);
        for (Map.Entry<String, Integer> data : resultMap.entrySet())
            System.out.println(data.getKey() + "=" + data.getValue());
    }

}
