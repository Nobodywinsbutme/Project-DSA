package com.maze;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
// Import GamePanel từ gói UI (đảm bảo bạn đã tạo class GamePanel ở phần trước)
import com.maze.ui.GamePanel; 

public class Main {
    
    // Cấu hình kích thước game
    public static final int ROWS = 15; // Số hàng
    public static final int COLS = 20; // Số cột

    public static void main(String[] args) {
        // Chạy giao diện trong luồng sự kiện của Swing
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Creating game"); // Log để biết game đang chạy

        // 1. Tạo khung cửa sổ chính
        JFrame frame = new JFrame("Maze Game - DSA Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // 2. Tạo GamePanel (Đây là phần code UI bạn viết)
        // GamePanel sẽ tự gọi new GameModel(ROWS, COLS) bên trong nó
        GamePanel gamePanel = new GamePanel(ROWS, COLS);
        
        // 3. Gắn Panel vào khung cửa sổ
        frame.add(gamePanel);

        // 4. Co giãn cửa sổ vừa khít nội dung
        frame.pack();
        
        // 5. Căn giữa màn hình và hiển thị
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // QUAN TRỌNG: Yêu cầu bàn phím tập trung vào Panel để điều khiển
        gamePanel.requestFocusInWindow();
    }
}