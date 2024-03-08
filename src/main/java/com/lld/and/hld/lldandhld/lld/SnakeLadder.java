package com.lld.and.hld.lldandhld.lld;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Multi members game
 * 1 to 100, initial positions
 * Snakes and ladder in mulitple positions - {head, tail} - initial positions
 * Dice thrown
 */

/**
   Snakes
 */
// class Snake {
//     int head, tail;
//  }

/**
 * Ladder
 */
// class Ladder {
// List<Pair> ladders;
// }

public class SnakeLadder {
    int[] playersPositions;
    boolean isGameOver;
    HashMap<Integer, Integer> ladders;
    HashMap<Integer, Integer> snakes;

    public SnakeLadder(int noOfPlayers) {

        playersPositions = new int[noOfPlayers];
        Arrays.fill(playersPositions, 0);

        ladders = new HashMap<>();
        ladders.put(3, 20);
        ladders.put(10, 28);
        ladders.put(30, 56);
        ladders.put(45, 78);
        ladders.put(78, 97);

        snakes = new HashMap<>();
        snakes.put(30, 5);
        snakes.put(44, 23);
        snakes.put(65, 18);
        snakes.put(90, 18);
        snakes.put(84, 33);

        isGameOver = false;
    }

    public void move(int player, int input) {

        int nextPosition = playersPositions[player] + input;

        if (nextPosition > 99) {
            System.out.println("Skipping");
            return;
        } 
        
        if (nextPosition == 99) {
            System.out.println("Player " + player + " is the winner");
            playersPositions[player] = nextPosition;
            isGameOver = true;
            return;
        }

        if (this.snakes.containsKey(nextPosition)) {
            this.playersPositions[player] = this.snakes.get(nextPosition);
        } else if (this.ladders.containsKey(nextPosition)) {
            this.playersPositions[player] = this.ladders.get(nextPosition);
        } else {
            playersPositions[player] = nextPosition;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 2;
        SnakeLadder snakeLadder = new SnakeLadder(2);
        Random random = new Random();
        int input;

        try (Scanner scanner = new Scanner(System.in)) {

            while (!snakeLadder.isGameOver) {
                for (int i = 0; i < N && (!snakeLadder.isGameOver); i++) {
                    input = random.nextInt(6) + 1;
                    System.out.println("Input for player :" + i + " is " + input);
                    System.out.println("Current position of player :" + i + " is " + snakeLadder.playersPositions[i]);
                    snakeLadder.move(i, input);
                    Thread.sleep(300);
                    System.out.println(
                            "After update, position of player :" + i + " is " + snakeLadder.playersPositions[i]);
                }
            }
        }
    }
}
