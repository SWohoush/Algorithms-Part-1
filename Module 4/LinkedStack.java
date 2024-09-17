import java.nio.BufferUnderflowException;

public class LinkedStack<Item> {
    private int size = 0;
    private Node first = null;

    public class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public int getSize() {
        return size;
    }
}

