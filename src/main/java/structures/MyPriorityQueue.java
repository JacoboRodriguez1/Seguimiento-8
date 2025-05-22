package structures;

public class MyPriorityQueue<T extends Comparable<T>> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;

    public void insert(T element) {
        Node newNode = new Node(element);
        if (head == null || element.compareTo(head.data) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null && element.compareTo(current.next.data) >= 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public T poll() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        return data;
    }

    public T peek() {
        return (head == null) ? null : head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
