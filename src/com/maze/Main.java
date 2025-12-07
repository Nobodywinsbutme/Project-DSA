package com.maze;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Generating maze...");
        
        // Create a maze with 10 rows and 10 columns
        MazeGenerator generator = new MazeGenerator(10, 10);
        generator.generateMaze();
        
        System.out.println("Maze generated successfully!");
        
        // Debug: Check the start cell (0,0)
        Cell start = generator.getGrid()[0][0];
        
        // If false, it means the wall is broken (open path)
        System.out.println("Start Cell (0,0) - Right Wall: " + start.walls[1]); 
        System.out.println("Start Cell (0,0) - Bottom Wall: " + start.walls[2]);
    }
}
