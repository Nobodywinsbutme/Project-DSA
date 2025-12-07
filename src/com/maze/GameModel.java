package com.maze;

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

    // --- Getters for IU ---
    public Cell[][] getGrid() { return grid; }
    public Player getPlayer() { return player; }
    public boolean isLevelComplete() { return isGameOver; }
}