package com.lld.and.hld.lldandhld.company.atlassian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  {
//     maxPreferenceCount: 1,
//     mediumPreferenceCount: 1,
//     minPreferenceCount: 1,
//     totalVoteCount: 
//  }
/**
 * 3, 2, 1
 * x - (A, B, C) => A=3,
 * Y - (B, C, A) => A = 4, B = 5, C =3 Max, HighestVotesCandidate,
 * HashMap(candidate, voteCount)
 * List<List<Character>>
 */

class VoteCount {
    int maxPreferenceCount, mediumPreferenceCount, minPreferenceCount, totalVoteCount;

    VoteCount() {
        maxPreferenceCount = mediumPreferenceCount = minPreferenceCount = totalVoteCount = 0;
    }
}

public class Vote {

    public static String findElectionWinner(List<List<String>> votes) {

        int maxVoteCount = 0;
        Boolean sameMaxVoteCount = false;
        List<String> sameMaxVoteGotCandidates = new ArrayList<>();
        String maxVoteCandidate = null;
        Map<String, VoteCount> map = new HashMap<>();

        for (int i = 0; i < votes.size(); i++) {
            for (int j = 0, x = 3; j < votes.get(i).size(); j++, x--) {

                String candidate = votes.get(i).get(j);
                VoteCount voteCount = null;

                if (map.containsKey(candidate)) {
                    voteCount = map.get(candidate);
                } else {
                    voteCount = new VoteCount();
                }

                if (x == 3) {
                    voteCount.maxPreferenceCount++;
                } else if (x == 2) {
                    voteCount.mediumPreferenceCount++;
                } else if (x == 1) {
                    voteCount.minPreferenceCount++;
                }

                voteCount.totalVoteCount += x;
                map.put(candidate, voteCount);

                if (voteCount.totalVoteCount > maxVoteCount) {

                    maxVoteCount = voteCount.totalVoteCount;
                    maxVoteCandidate = candidate;
                    sameMaxVoteCount = false;
                    sameMaxVoteGotCandidates = new ArrayList<>();
                    sameMaxVoteGotCandidates.add(candidate);
                } else if (voteCount.totalVoteCount == maxVoteCount) {
                    sameMaxVoteCount = true;
                    sameMaxVoteGotCandidates.add(candidate);
                }
            }
        }

        if (sameMaxVoteCount && sameMaxVoteGotCandidates.size() > 1) {
            int maxPreferenceCount = 0;
            for (int i = 0; i < sameMaxVoteGotCandidates.size(); i++) {
                VoteCount voteCount = map.get(sameMaxVoteGotCandidates.get(i));
                if (voteCount.maxPreferenceCount > maxPreferenceCount) {
                    maxVoteCandidate = sameMaxVoteGotCandidates.get(i);
                    maxPreferenceCount = voteCount.maxPreferenceCount;
                }
            }
        }
        return maxVoteCandidate;
    }

    public static void main(String[] args) {
        List<List<String>> votes = new ArrayList<List<String>>();
        votes.add(Arrays.asList("A", "B", "C"));
        votes.add(Arrays.asList("A", "C", "B"));
        votes.add(Arrays.asList("B", "C", "D"));
        System.out.println("Winner is " + findElectionWinner(votes));
    }

}