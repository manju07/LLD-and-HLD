package com.lld.and.hld.lldandhld.snakegame;

import java.util.Scanner;

import lombok.Data;

@Data
public class SnakeGame {
    private Board board;
    private Snake snake;
    private int direction;

    public SnakeGame(int rows, int cols) {
        this.board = new Board(rows, cols);
        this.snake = new Snake(new Cell(0, 0, CellType.SNAKE_CELL));
    }

    public void update() {
        Cell head = snake.getHead();
        Cell nextCell = getNextCell(head);

        if (snake.checkCrash(nextCell)) {

            snake.setGameOver(true);

        } else {

            Cell boardCell = board.getCells()[nextCell.getRow()][nextCell.getColumn()];

            if (CellType.FOOD.equals(boardCell.getCellType())) {

                snake.grow();

                while (true) {
                    Cell generatedFoodCell = board.generateFood();
                    if (!snake.checkCrash(generatedFoodCell))
                        break;
                }
            }
            snake.move(nextCell);
        }
    }

    public Cell getNextCell(Cell cell) {
        int nextRow = cell.getRow();
        int nextCol = cell.getColumn();
        if (direction != -1) {
            if (direction == 1)
                nextCol++;
            else if (direction == -1)
                nextCol--;
            else if (direction == 2)
                nextRow--;
            else if (direction == -2)
                nextRow++;
            Cell nextCell = new Cell(nextRow, nextCol, CellType.EMPTY);
            System.out.println("NextCell = " + nextCell);
            return nextCell;
        }
        snake.setGameOver(true);
        return null;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(4, 4);
        snakeGame.board.generateFood();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            System.out.println(" ---- Direction --- ");
            snakeGame.direction = scanner.nextInt();
            snakeGame.update();
        }

    }
}
