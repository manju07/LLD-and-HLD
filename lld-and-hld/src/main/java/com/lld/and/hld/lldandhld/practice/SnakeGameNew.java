package com.lld.and.hld.lldandhld.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.util.Pair;
import lombok.Data;

/**
 * Matrix - size N * M
 *      - Snake Occupied, EMPTY
 * Snake -> head points to begining of the LL, body(LinkedList)
 *  operations:
 *      - grow() -> add at back
 *      - crash(newPoint) -> go over the mat
 *      - move(newPoint) -> remove from back, add at back
 *  
 * Game:
 *      Initialize the matrix, snake and its default position in matrix
 *      
 *      input for moving -> L, R, T, D
 * 
 */

/**
 * InnerSnakeGameNew
 */
// @Data
class Board {
    int rows;
    int cols;
    int matrix[][];

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], 0);
        }
    }

    public void foodGenerator() {
        int row = 0, col = 0;
        while (true) {
            row = new Random().nextInt(rows);
            col = new Random().nextInt(cols);
            if (matrix[row][col] == 0) {
                break;
            }
        }
        matrix[row][col] = 2;
    }

    // private void InitializeBoard() {
    // for (int i = 0; i < rows; i++) {
    // for (int j = 0; j < cols; j++) {

    // }
    // }
    // }
}

/**
 * Snake
 */
class Snake {
    LinkedList<Pair<Integer, Integer>> body;
    Pair<Integer, Integer> snakeHead;

    Snake(List<Pair<Integer, Integer>> snakeInitialPositions) {
        body = new LinkedList<>(snakeInitialPositions);
        snakeHead = snakeInitialPositions.get(0);
    }

    void grow() {
        body.addFirst(snakeHead);
    }

    boolean crashCheck(Pair<Integer, Integer> newMove) {
        for (int i = 0; i < body.size(); i++) {
            Pair<Integer, Integer> point = body.get(i);
            if (newMove.getKey() == point.getKey() && newMove.getValue() == point.getValue()) {
                return true;
            }
        }
        return false;
    }

    Pair<Integer, Integer> move(Pair<Integer, Integer> newMove) {
        Pair<Integer, Integer> tail = body.removeLast();
        body.addFirst(newMove);
        snakeHead = newMove;
        return tail;
    }

}

public class SnakeGameNew {

    static boolean isGameOver = false;
    static Board board = new Board(4, 4);
    static Snake snake = new Snake(Arrays.asList(new Pair<Integer, Integer>(2, 0), new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(0, 0)));

    // , new Pair<Integer, Integer>(2, 1),
    // new Pair<Integer, Integer>(2, 0)

    static Pair<Integer, Integer> findNextMove(int input) {
        Pair<Integer, Integer> snakeHead = snake.snakeHead;
        int nextRow = snakeHead.getKey(), nextCol = snakeHead.getValue();

        switch (input) {
            // Left
            case 1:
                nextCol -= 1;
                break;
            // Right
            case 2:
                nextCol += 1;
                break;
            // top
            case 3:
                nextRow -= 1;
                break;
            // down
            case 4:
                nextRow += 1;
                break;
            default:
                isGameOver = true;
                break;
        }

        if (nextRow >= board.rows) {
            nextRow = 0;
        }

        if (nextCol >= board.cols) {
            nextCol = 0;
        }

        if (nextRow < 0) {
            nextRow = board.rows - 1;
        }

        if (nextCol < 0) {
            nextCol = board.cols - 1;
        }

        return new Pair<Integer, Integer>(nextRow, nextCol);
    }

    static void nextMove(int input) {

        Pair<Integer, Integer> nextMove = findNextMove(input);

        System.out.println("nextMove = " + nextMove);

        if (board.matrix[nextMove.getKey()][nextMove.getValue()] == 1 || snake.crashCheck(nextMove)) {
            isGameOver = true;
            return;
        }

        Pair<Integer, Integer> tail = snake.move(nextMove);
        board.matrix[tail.getKey()][tail.getValue()] = 0;
    }

    public static void main(String[] args) {
        int input = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            int movesCount = 0;
            System.out.println("snake.body = " + snake.body);
            while (!isGameOver) {
                System.out.println("Please enter your input for next move");
                System.out.println("1. Left");
                System.out.println("2. Right");
                System.out.println("3. Top");
                System.out.println("4. Down");
                System.out.println("5. Game over");
                input = scanner.nextInt();
                nextMove(input);
                movesCount++;
                if (movesCount == 5) {
                    snake.grow();
                    movesCount = 0;
                }
                System.out.println("snake.body = " + snake.body);
                System.out.println("snake.snakeHead = " + snake.snakeHead);
                System.out.println("matrix = " + board.matrix);
            }
        }
        System.out.println("Game is over");
    }

}
