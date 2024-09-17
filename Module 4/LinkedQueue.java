import java.nio.BufferUnderflowException;

public class LinkedQueue<Item> {
    private int size = 0;
    private Node first = null;
    private Node last = null;

    public class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new BufferUnderflowException();
        Item item = first.item;
        if (first == last)
            last = null;
        first = first.next;
        size--;
        return item;
    }
}
