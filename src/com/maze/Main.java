package com.maze;

public class Main {
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
    }
}
