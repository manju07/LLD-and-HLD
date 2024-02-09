package com.lld.and.hld.lldandhld.lld;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import lombok.Data;
import lombok.ToString;

enum CellType {
    EMPTY, FOOD, SNAKE_CELL
}

@Data
@ToString
class Cell {
    private int row;
    private int column;
    private CellType cellType;

    public Cell(int row, int column, CellType cellType) {
        this.row = row;
        this.column = column;
        this.cellType = cellType;
    }
}

@Data
class Board {
    private Cell[][] cells;
    private int rows, cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j, CellType.EMPTY);
            }
        }
    }

    public Cell generateFood() {
        Random random = new Random();
        int row = random.nextInt(rows);
        int col = random.nextInt(cols);

        Cell cell = new Cell(row, col, CellType.FOOD);
        cells[row][col] = cell;
        System.out.println("Food Generated cell = " + cell);
        return cell;

    }
}

@Data
class Snake {
    private Cell head;
    private LinkedList<Cell> snakeBody;
    private boolean gameOver;

    public Snake(Cell initialPosition) {

        head = initialPosition;

        snakeBody = new LinkedList<>();
        snakeBody.add(initialPosition);
        head.setCellType(CellType.SNAKE_CELL);
    }

    public void grow() {
        snakeBody.add(head);
    }

    public void move(Cell nextCell) {
        Cell tail = snakeBody.removeLast();
        tail.setCellType(CellType.EMPTY);

        head = nextCell;
        head.setCellType(CellType.SNAKE_CELL);
        snakeBody.add(head);
        System.out.println("Head position = " + head);
        System.out.println("Snake body = " + snakeBody);
    }

    public boolean checkCrash(Cell nextPosition) {
        for (Cell cell : snakeBody) {
            if (cell.equals(nextPosition)) {
                return true;
            }
        }
        return false;
    }
}

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
