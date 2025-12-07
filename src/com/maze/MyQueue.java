package com.maze;

public class MyQueue {
    private Node front, rear;
    private int size;

    public MyQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }

    // add an item to tail the queue
    public void enqueue(Cell cell) {
        Node newNode = new Node(cell);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // remove an item from top of the queue
    public Cell dequeue() {
        if (isEmpty()) return null;
        
        Cell data = front.data;
        front = front.next;
        
        if (front == null) rear = null;
        
        size--;
        return data;
    }

    public boolean isEmpty() { return front == null; }
    public int getSize() {
        return size;
    }
}