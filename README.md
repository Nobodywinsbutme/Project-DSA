# Maze Generator & Solver - DSA Project

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

A Java-based application developed for the **Data Structures and Algorithms** course. This project demonstrates core computer science concepts through an interactive Maze Game, featuring random maze generation, a playable character, undo functionality, and an intelligent hint system.

---

## 🚀 Key Features (DSA Implementation)

### 1. Maze Generation
- **Algorithm:** Recursive Backtracker (Depth-First Search - DFS).
- **DSA Concept:** Uses a **Custom Stack** (LIFO) implemented via Linked List to backtrack when hitting dead ends.
- **Result:** Generates a perfect maze (fully accessible, no loops) every time.

### 2. Smart Hint System
- **Algorithm:** Breadth-First Search (BFS).
- **DSA Concept:** Uses a **Custom Queue** (FIFO) implemented via Linked List to explore neighbor nodes layer by layer.
- **Result:** Calculates and suggests the *shortest path* to the exit from the player's current position.


---

## 🎮 How to Play

1. **Start the Game:** Run the `Main.java` file.
2. **Controls:**
   - **Arrow Keys (↑ ↓ ← →):** Move the character (Red Dot).
   - **H Key:** Get a Hint (Shows the direction to the exit).
   - **A Key:** Start auto-solve — the game will automatically move the player along the shortest path to the exit (calculated with BFS). Press A again to stop auto-solve.
3. **Objective:** Navigate from the starting point (Top-Left) to the exit (Bottom-Right).

---

## 📂 Project Structure

The project follows a clean **MVC** (Model-View-Controller) architecture and is organized into packages:

```text
src/com/maze
├── Main.java              # Entry point
├── ds/                    # Custom Data Structures (Manual Implementation)
│   ├── MyStack.java       # Custom Stack (Linked List based)
│   ├── MyQueue.java       # Custom Queue (Linked List based)
│   └── Node.java          # Node class for data structures
├── entities/              # Game Objects
│   ├── Cell.java          # Represents a maze unit (walls, state)
│   └── Player.java        # Handles coordinates & movement history
├── logic/                 # Core Algorithms
│   ├── MazeGenerator.java # Recursive Backtracker Algorithm
│   └── GameModel.java     # Game Rules & State Management
└── ui/                    # User Interface
    └── GamePanel.java     # Rendering & Input Handling (Java Swing)
```
## 🛠️ Tech Stack
1. Language: Java (JDK 17 or higher recommended).

2. GUI Library: Java Swing / AWT.

3. Version Control: Git & GitHub.

4. IDE: Visual Studio Code / IntelliJ IDEA.
---

## 👥 Team MembersMember 

|Name|Role|Responsibilities|
|---|---|---|
|**Phạm Trung Kiên** |Backend & Core Algorithms|Maze Generation (DFS), Game Logic, Custom Data Structures (Stack/Queue) and Hint System.|
|**Lê Đoàn Minh Ngọc**|Frontend & Solver|UI/UX Design (Swing), Input Handling, Solver Animation/Visualization.|

---

## 📝 Acknowledgments
- This project was created as a final assignment for the Data Structures and Algorithms subject.

- Note: We strictly avoided using built-in Java collections like java.util.Stack or java.util.LinkedList for the core logic. Instead, we manually implemented these data structures to demonstrate a deep understanding of memory management and node-based linking.

**© 2025 Maze Game Project. All rights reserved.**