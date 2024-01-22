package com.lld.and.hld.lldandhld.snakegame;

import java.util.LinkedList;

import lombok.Data;

@Data
public class Snake {
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
