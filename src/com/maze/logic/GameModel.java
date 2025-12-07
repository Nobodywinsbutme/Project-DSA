package com.maze.logic;

import com.maze.ds.MyQueue;
import com.maze.entities.Cell;
import com.maze.entities.Player;

public class GameModel {
    private MazeGenerator mazeGen;
    private Player player;
    private Cell[][] grid;
    private boolean isGameOver = false;

    // Constructor: New game
    public GameModel(int rows, int cols) {
        restart(rows, cols);
    }

    // move function
    public void movePlayer(String direction) {
        if (isGameOver) return; // Stop if game over

        switch (direction.toUpperCase()) {
            case "UP":    player.moveUp(grid); break;
            case "DOWN":  player.moveDown(grid); break;
            case "LEFT":  player.moveLeft(grid); break;
            case "RIGHT": player.moveRight(grid); break;
        }

        checkWinCondition();
    }

    // Check win condition
    private void checkWinCondition() {
        Cell end = mazeGen.getEndCell();
        if (player.getRow() == end.row && player.getCol() == end.col) {
            isGameOver = true;
            System.out.println("VICTORY! You found the exit!");
        }
    }

    // Rstart game (New maze + reset player)
    public void restart(int rows, int cols) {
        // 1. Create new maze
        mazeGen = new MazeGenerator(rows, cols);
        mazeGen.generateMaze();
        this.grid = mazeGen.getGrid();
        
        // 2. Set player to start position
        Cell start = mazeGen.getStartCell();
        player = new Player(start.row, start.col);
        
        isGameOver = false;
        System.out.println("New Game Started!");
    }

    // Hint next move using BFS
    public Cell getHint() {
        Cell start = grid[player.getRow()][player.getCol()];
        Cell end = mazeGen.getEndCell();

        // 1. Khởi tạo cấu trúc dữ liệu cho BFS
        MyQueue queue = new MyQueue();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Cell[][] parent = new Cell[grid.length][grid[0].length]; // find path

        // 2. start BFS from current player position
        queue.enqueue(start);
        visited[start.row][start.col] = true;

        boolean found = false;
        while (!queue.isEmpty()) {
            Cell current = queue.dequeue();

            // check if reached end
            if (current == end) {
                found = true;
                break;
            }

            // check 4 possible directions
            addNeighborIfValid(current, -1, 0, visited, parent, queue); // Lên
            addNeighborIfValid(current, 1, 0, visited, parent, queue);  // Xuống
            addNeighborIfValid(current, 0, -1, visited, parent, queue); // Trái
            addNeighborIfValid(current, 0, 1, visited, parent, queue);  // Phải
        }

        if (found) {
            // 3. check path back to find next move(from start)
            Cell curr = end;
            while (parent[curr.row][curr.col] != start) {
                curr = parent[curr.row][curr.col];
            }
            return curr; 
        }
        
        return null; // No path found
    }

    // function to add neighbor if valid (helper for BFS)
    private void addNeighborIfValid(Cell current, int dRow, int dCol, boolean[][] visited, Cell[][] parent, MyQueue queue) {
        int r = current.row + dRow;
        int c = current.col + dCol;

        // Check 1: in-bounds
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return;
        
        // Check 2: didn't visit
        if (visited[r][c]) return;

        // Check 3: no wall between current and neighbor
        // Logic wall: 0-TOP, 1-RIGHT, 2-BOTTOM, 3-LEFT
        boolean hasWall = false;
        if (dRow == -1) hasWall = current.walls[0]; // Đi lên check tường trên
        if (dRow == 1)  hasWall = current.walls[2]; // Đi xuống check tường dưới
        if (dCol == 1)  hasWall = current.walls[1]; // Đi phải check tường phải
        if (dCol == -1) hasWall = current.walls[3]; // Đi trái check tường trái
        
        if (!hasWall) {
            visited[r][c] = true;
            parent[r][c] = current; // set parent for path tracking
            queue.enqueue(grid[r][c]);
        }
    }

    // --- Getters for IU ---
    public Cell[][] getGrid() { return grid; }
    public Player getPlayer() { return player; }
    public boolean isLevelComplete() { return isGameOver; }
}