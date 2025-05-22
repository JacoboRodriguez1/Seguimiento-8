package structures;

public class MyQueue<T> {

    private class Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    private Node front, rear;
    private int size;

    public void enqueue(T element) {
        Node node = new Node(element);
        if (rear != null)
            rear.next = node;
        rear = node;
        if (front == null)
            front = node;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T data = front.data;
        front = front.next;
        if (front == null)
            rear = null;
        size--;
        return data;
    }

    public T peek() {
        return isEmpty() ? null : front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
