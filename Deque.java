import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private DLLNode firstNode;
    private DLLNode lastNode;

    private class DLLNode {
        Item item;
        DLLNode frontNode;
        DLLNode backNode;
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return firstNode == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        DLLNode oldFirst = firstNode;
        firstNode = new DLLNode();
        firstNode.item = item;
        firstNode.frontNode = oldFirst;
        firstNode.backNode = null;
        if (lastNode == null) {
            lastNode = firstNode;
        } else {
            oldFirst.backNode = firstNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        DLLNode oldLast = lastNode;
        lastNode = new DLLNode();
        lastNode.item = item;
        lastNode.frontNode = null;
        lastNode.backNode = oldLast;
        if (firstNode == null) {
            firstNode = lastNode;
        } else {
            oldLast.frontNode = lastNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = firstNode.item;
        if (firstNode == lastNode) {
            lastNode = null;
            firstNode = null;
        } else {
            firstNode = firstNode.frontNode;
            firstNode.backNode = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = lastNode.item;
        if (firstNode == lastNode) {
            lastNode = null;
            firstNode = null;
        } else {
            lastNode = lastNode.backNode;
            lastNode.frontNode = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private DLLNode index = firstNode;

        public boolean hasNext() {
            return index != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = index.item;
            index = index.frontNode;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        StdOut.println(deque.isEmpty());
        StdOut.println(deque.size());
        deque.addFirst("1");
        deque.addLast("2");
        deque.addLast("3");
        deque.addLast("4");
        deque.addLast("5");
        deque.addLast("6");
        deque.addLast("7");
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        for (String d : deque) {
            StdOut.println(d);
        }
    }
}
