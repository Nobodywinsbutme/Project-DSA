package com.maze.ds;

import com.maze.entities.Cell;

public class MyStack {
    private Node top;
    private int size;
    public MyStack() { this.top = null; this.size = 0; }

    // add an item to the top of the stack
    public void push(Cell cell) {
        Node newNode = new Node(cell);
        if (top == null) top = newNode;
        else { newNode.next = top; top = newNode; }
        size++;
    }

    // remove and return the top item from the stack
    public Cell pop() {
        if (isEmpty()) return null;
        Cell data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() { return top == null; }
    public int getSize() { return size; }
}