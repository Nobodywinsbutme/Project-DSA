package com.maze;

public class Cell {
    public int row, col;
    // Walls order: 0-TOP, 1-RIGHT, 2-BOTTOM, 3-LEFT
    // true = wall exists, false = no wall (open path)
    public boolean[] walls = {true, true, true, true};
    
    // Used for the generation algorithm to track visited cells
    public boolean visited = false;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}