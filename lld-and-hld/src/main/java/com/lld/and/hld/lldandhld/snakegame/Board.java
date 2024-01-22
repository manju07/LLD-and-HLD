package com.lld.and.hld.lldandhld.snakegame;

import java.util.Random;

import lombok.Data;

@Data
public class Board {
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
