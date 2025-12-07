package com.maze;

public class Player {
    private int row;
    private int col;
    private int score = 0;

    // create player at starting position
    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    // --- Move function(Logic check wall) ---
    
    // Wanna move up -> Check top wall (Index 0)
    public void moveUp(Cell[][] grid) {
        // check: if no top wall then can move up
        if (!grid[row][col].walls[0]) {
            row--;
            score++; // plus score on successful move
        }
    }

    // Wanna move right -> Check right wall (Index 1)
    public void moveRight(Cell[][] grid) {
        if (!grid[row][col].walls[1]) {
            col++;
            score++;
        }
    }

    // Wanna move down -> Check bottom wall (Index 2)
    public void moveDown(Cell[][] grid) {
        if (!grid[row][col].walls[2]) {
            row++;
            score++;
        }
    }

    // Wanna move left -> Check left wall (Index 3)
    public void moveLeft(Cell[][] grid) {
        if (!grid[row][col].walls[3]) {
            col--;
            score++;
        }
    }

    // Get current position and score
    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getScore() { return score; }
}