package com.maze.logic;

import java.util.ArrayList;
import java.util.Random;

import com.maze.ds.MyStack;
import com.maze.entities.Cell;

public class MazeGenerator {
    private int rows, cols;
    private Cell[][] grid;
    private Cell startCell;
    private Cell endCell;
    private Random random = new Random();

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];

        // Initialize the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    /**
     * Generates the maze using the Recursive Backtracker algorithm.
     */
    public void generateMaze() {
        MyStack stack = new MyStack();
        Cell current = grid[0][0]; // Start from top-left
        current.visited = true;
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.pop();
            ArrayList<Cell> neighbors = getUnvisitedNeighbors(current);

            if (!neighbors.isEmpty()) {
                stack.push(current); // Push current back to stack as we might return here
                
                // 1. Choose a random unvisited neighbor
                Cell next = neighbors.get(random.nextInt(neighbors.size()));
                
                // 2. Remove walls between current cell and the chosen neighbor
                removeWalls(current, next);
                
                // 3. Mark the neighbor as visited and push to stack
                next.visited = true;
                stack.push(next);
            }
        }
        
        // Reset visited status so the Solver can use it later
        resetVisited();
        // Define start and end cells
        startCell = grid[0][0];
        endCell = grid[rows - 1][cols - 1];
        // Open entrance and exit
        endCell.walls[1] = false;

    }

    private ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        int r = c.row;
        int col = c.col;

        // Check 4 directions: Top, Right, Bottom, Left
        
        // Top
        if (r > 0 && !grid[r - 1][col].visited) {
            neighbors.add(grid[r - 1][col]);
        }
        // Right
        if (col < cols - 1 && !grid[r][col + 1].visited) {
            neighbors.add(grid[r][col + 1]);
        }
        // Bottom
        if (r < rows - 1 && !grid[r + 1][col].visited) {
            neighbors.add(grid[r + 1][col]);
        }
        // Left
        if (col > 0 && !grid[r][col - 1].visited) {
            neighbors.add(grid[r][col - 1]);
        }

        return neighbors;
    }

    private void removeWalls(Cell a, Cell b) {
        int x = a.col - b.col;
        int y = a.row - b.row;

        // b is to the right of a
        if (x == -1) {
            a.walls[1] = false; // Remove right wall of a
            b.walls[3] = false; // Remove left wall of b
        }
        // b is to the left of a
        if (x == 1) {
            a.walls[3] = false;
            b.walls[1] = false;
        }
        // b is below a
        if (y == -1) {
            a.walls[2] = false;
            b.walls[0] = false;
        }
        // b is above a
        if (y == 1) {
            a.walls[0] = false;
            b.walls[2] = false;
        }
    }

    // Reset visited flags for the solver
    private void resetVisited() {
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c].visited = false;
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getStartCell() { return startCell; }
    public Cell getEndCell() { return endCell; }
}