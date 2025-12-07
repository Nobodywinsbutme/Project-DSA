package com.maze;

public class Player {
    private int row;
    private int col;
    // Điểm số (tùy chọn cho game)
    private int score = 0;

    // Khởi tạo nhân vật tại vị trí bắt đầu (thường là 0,0)
    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    // --- CÁC HÀM DI CHUYỂN (Logic kiểm tra tường) ---
    
    // Muốn đi lên -> Phải kiểm tra xem ô hiện tại có tường bên TRÊN (Index 0) không?
    public void moveUp(Cell[][] grid) {
        // Kiểm tra: Nếu không có tường bên trên (walls[0] == false) thì mới cho đi
        if (!grid[row][col].walls[0]) {
            row--;
            score++; // Ví dụ: mỗi bước đi được cộng điểm
        }
    }

    // Muốn đi phải -> Kiểm tra tường bên PHẢI (Index 1)
    public void moveRight(Cell[][] grid) {
        if (!grid[row][col].walls[1]) {
            col++;
            score++;
        }
    }

    // Muốn đi xuống -> Kiểm tra tường bên DƯỚI (Index 2)
    public void moveDown(Cell[][] grid) {
        if (!grid[row][col].walls[2]) {
            row++;
            score++;
        }
    }

    // Muốn đi trái -> Kiểm tra tường bên TRÁI (Index 3)
    public void moveLeft(Cell[][] grid) {
        if (!grid[row][col].walls[3]) {
            col--;
            score++;
        }
    }

    // Getters để lấy vị trí hiện tại (cho phần Vẽ UI)
    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getScore() { return score; }
}