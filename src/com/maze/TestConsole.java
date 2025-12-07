package com.maze;

import com.maze.entities.Cell;
import com.maze.logic.GameModel;

public class TestConsole {
    public static void main(String[] args) throws Exception {
    // Khởi tạo Game Model
        GameModel game = new GameModel(10, 10);
        
        System.out.println("Game Start!");
        
        // --- ĐOẠN DEBUG ---
        Cell start = game.getGrid()[0][0];
        System.out.println("--- Wall Inspection at (0,0) ---");
        System.out.println("Wall UP:    " + start.walls[0]);
        System.out.println("Wall RIGHT: " + start.walls[1]);
        System.out.println("Wall DOWN:  " + start.walls[2]);
        System.out.println("Wall LEFT:  " + start.walls[3]);
        System.out.println("------------------------------");
        // Lưu ý: true là có tường (bị chặn), false là đường đi.

        System.out.println("Player at: " + game.getPlayer().getRow() + ", " + game.getPlayer().getCol());
        
        // Thử di chuyển theo hướng KHÔNG có tường (dựa vào log trên)
        // Ví dụ: Nếu Wall RIGHT là false, hãy thử move RIGHT
        System.out.println("Attempting to move...");
        if (!start.walls[1]) {
             System.out.println("Moving RIGHT...");
             game.movePlayer("RIGHT");
        } else if (!start.walls[2]) {
             System.out.println("Moving DOWN...");
             game.movePlayer("DOWN");
        } else {
            System.out.println("Stuck? Check logic generator!");
        }
        
        System.out.println("Player now at: " + game.getPlayer().getRow() + ", " + game.getPlayer().getCol());

        System.out.println("Player at: " + game.getPlayer().getRow() + "," + game.getPlayer().getCol());
    
        System.out.println("--- ASKING FOR HINT ---");
        Cell hint = game.getHint();
        if (hint != null) {
            System.out.println("Hint says: Go to " + hint.row + "," + hint.col);
            
            // Thử kiểm tra xem nó bảo đi hướng nào
            if (hint.row < game.getPlayer().getRow()) System.out.println("-> Suggestion: GO UP");
            if (hint.row > game.getPlayer().getRow()) System.out.println("-> Suggestion: GO DOWN");
            if (hint.col > game.getPlayer().getCol()) System.out.println("-> Suggestion: GO RIGHT");
            if (hint.col < game.getPlayer().getCol()) System.out.println("-> Suggestion: GO LEFT");
        } else {
            System.out.println("No path found!");
        }

    }
}