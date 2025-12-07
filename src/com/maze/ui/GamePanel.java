package com.maze.ui;


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Import các package backend của bạn bè
import com.maze.logic.GameModel;
import com.maze.entities.Cell;
import com.maze.entities.Player;

public class GamePanel extends JPanel implements KeyListener {

    // 1. Khai báo các biến cấu hình
    private GameModel gameModel;
    private final int CELL_SIZE = 30; // Kích thước mỗi ô (pixel)
    private int rows, cols;
    private Cell currentHint = null;
    // Auto-solve state
    private boolean autoRunning = false;
    private Timer autoTimer = null;
    private List<Cell> autoPath = null;
    private int autoIndex = 0;
    // Màu sắc giao diện (Có thể tùy chỉnh)
    private final Color WALL_COLOR = Color.WHITE;
    private final Color PLAYER_COLOR = Color.RED;
    private final Color BACKGROUND_COLOR = Color.BLACK;

    public GamePanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        
        // Khởi tạo kích thước Panel dựa trên số ô và kích thước ô
        this.setPreferredSize(new Dimension(cols * CELL_SIZE, rows * CELL_SIZE));
        this.setBackground(BACKGROUND_COLOR);
        
        // Khởi tạo Logic Game từ Backend
        this.gameModel = new GameModel(rows, cols);

        // Cấu hình sự kiện bàn phím
        this.setFocusable(true); // BẮT BUỘC: để Panel nhận được sự kiện phím
        this.addKeyListener(this);
    }

    // 2. Phần Vẽ (Render) - Chạy liên tục khi repaint() được gọi
   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Cell[][] grid = gameModel.getGrid();

        // 1. Vẽ Mê cung (Code cũ giữ nguyên)
        g2d.setColor(WALL_COLOR);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                drawCell(g2d, grid[r][c]);
            }
        }

        // --- THÊM ĐOẠN NÀY: VẼ GỢI Ý ---
        if (currentHint != null) {
            // Chọn màu nổi bật (ví dụ: Xanh lá cây sáng)
            g2d.setColor(Color.GREEN); 
            
            int x = currentHint.col * CELL_SIZE;
            int y = currentHint.row * CELL_SIZE;
            
            // Vẽ một hình vuông nhỏ hơn ô một chút nằm ở giữa
            int padding = 8;
            g2d.fillOval(x + padding, y + padding, CELL_SIZE - (padding*2), CELL_SIZE - (padding*2));
        }
        // -------------------------------

        // 3. Vẽ Nhân vật (Code cũ giữ nguyên)
        drawPlayer(g2d);
    }

    // Helper: Vẽ từng ô dựa trên mảng walls[]
    private void drawCell(Graphics2D g2, Cell cell) {
        int x = cell.col * CELL_SIZE;
        int y = cell.row * CELL_SIZE;

        // Giả sử quy ước walls: 0-TOP, 1-RIGHT, 2-BOTTOM, 3-LEFT
        boolean[] walls = cell.walls; 

        if (walls[0]) g2.drawLine(x, y, x + CELL_SIZE, y);             // Top
        if (walls[1]) g2.drawLine(x + CELL_SIZE, y, x + CELL_SIZE, y + CELL_SIZE); // Right
        if (walls[2]) g2.drawLine(x + CELL_SIZE, y + CELL_SIZE, x, y + CELL_SIZE); // Bottom
        if (walls[3]) g2.drawLine(x, y + CELL_SIZE, x, y);             // Left
    }

    // Helper: Vẽ nhân vật
    private void drawPlayer(Graphics2D g2) {
        Player player = gameModel.getPlayer(); // Giả sử GameModel có hàm getPlayer()
        
        // Tính toán vị trí vẽ (cộng thêm padding để nhân vật nằm lọt trong ô)
        int padding = 4;
        int x = player.getCol() * CELL_SIZE + padding;
        int y = player.getRow() * CELL_SIZE + padding;
        int size = CELL_SIZE - (padding * 2);

        g2.setColor(PLAYER_COLOR);
        g2.fillOval(x, y, size, size); // Vẽ hình tròn đại diện Player
    }

    // 3. Xử lý sự kiện bàn phím (Controller)
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // If auto-run is active, allow only A to toggle/stop it
        if (autoRunning) {
            if (key == KeyEvent.VK_A) {
                // Stop auto-run
                if (autoTimer != null) autoTimer.stop();
                autoRunning = false;
                System.out.println("Auto-solve stopped by user.");
            }
            return;
        }

        // Reset gợi ý cũ khi di chuyển (để đỡ rối mắt)
        if (key != KeyEvent.VK_H) {
            currentHint = null;
        }

        switch (key) {
            case KeyEvent.VK_UP:    gameModel.movePlayer("UP"); break;
            case KeyEvent.VK_DOWN:  gameModel.movePlayer("DOWN"); break;
            case KeyEvent.VK_LEFT:  gameModel.movePlayer("LEFT"); break;
            case KeyEvent.VK_RIGHT: gameModel.movePlayer("RIGHT"); break;

            case KeyEvent.VK_H:
                currentHint = gameModel.getHint();
                System.out.println("Hint requested!");
                break;

            case KeyEvent.VK_A:
                // Start auto-solve (BFS path then animate)
                autoPath = gameModel.getPathToExit();
                if (autoPath == null || autoPath.isEmpty()) {
                    System.out.println("No path to exit found.");
                    break;
                }

                autoRunning = true;
                autoIndex = 0;
                autoTimer = new Timer(180, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        if (autoIndex >= autoPath.size()) {
                            autoTimer.stop();
                            autoRunning = false;
                            return;
                        }

                        Cell next = autoPath.get(autoIndex++);
                        int dr = next.row - gameModel.getPlayer().getRow();
                        int dc = next.col - gameModel.getPlayer().getCol();

                        if (dr == -1) gameModel.movePlayer("UP");
                        else if (dr == 1) gameModel.movePlayer("DOWN");
                        else if (dc == -1) gameModel.movePlayer("LEFT");
                        else if (dc == 1) gameModel.movePlayer("RIGHT");

                        repaint();

                        if (gameModel.isLevelComplete()) {
                            autoTimer.stop();
                            autoRunning = false;
                            System.out.println("Auto-solve finished: reached exit");
                        }
                    }
                });
                autoTimer.start();
                break;
        }

        repaint(); // 3. Vẽ lại màn hình
        checkWinCondition(); // Kiểm tra thắng
    }

    private void checkWinCondition() {
        if (gameModel.isLevelComplete()) {
            System.out.println("You win! Level complete.");
            // stop auto-run if still running
            if (autoTimer != null && autoTimer.isRunning()) {
                autoTimer.stop();
                autoRunning = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }
}