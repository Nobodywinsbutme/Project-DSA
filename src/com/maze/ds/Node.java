package com.maze.ds;

import com.maze.entities.Cell;

public class Node {
    public Cell data;
    public Node next;
    public Node(Cell data) { this.data = data; this.next = null; }
}