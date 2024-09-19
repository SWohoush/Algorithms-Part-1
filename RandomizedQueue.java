import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private DLLNode firstNode;
    private DLLNode lastNode;

    private class DLLNode {
        Item item;
        DLLNode frontNode;
        DLLNode backNode;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return firstNode == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
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

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int num = StdRandom.uniformInt(0, size);
        DLLNode curr = firstNode;
        for (int i = 0; i < num; i++) {
            curr = curr.frontNode;
        }
        Item item = curr.item;
        if (firstNode == lastNode) {
            firstNode = null;
            lastNode = null;
        } else if (curr == firstNode) {
            firstNode = curr.frontNode;
            curr.frontNode.backNode = null;
            curr.frontNode = null;
        } else if (curr == lastNode) {
            lastNode = curr.backNode;
            curr.backNode.frontNode = null;
            curr.backNode = null;
        } else {
            curr.backNode.frontNode = curr.frontNode;
            curr.frontNode.backNode = curr.backNode;
            curr.backNode = null;
            curr.frontNode = null;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int num = StdRandom.uniformInt(0, size);
        DLLNode curr = firstNode;
        for (int i = 0; i < num; i++) {
            curr = curr.frontNode;
        }
        Item item = curr.item;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private DLLNode current = firstNode;
        int index = 0;
        private Item[] rq = (Item[]) new Object[size];

        public ListIterator() {
            for (int i = 0; i < size - 1; i++) {
                rq[i] = current.item;
                current = current.frontNode;
            }
            rq[size - 1] = current.item;
            StdRandom.shuffle(rq);
        }

        @Override
        public boolean hasNext() {
            return index != size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = rq[index++];
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQ = new RandomizedQueue<>();
        StdOut.println(randomQ.isEmpty());
        randomQ.enqueue(1);
        randomQ.enqueue(2);
        randomQ.enqueue(3);
        randomQ.enqueue(4);
        randomQ.enqueue(5);
        randomQ.enqueue(6);
        randomQ.enqueue(7);
        randomQ.enqueue(8);
        randomQ.enqueue(9);
        StdOut.println(randomQ.dequeue());
        StdOut.println(randomQ.dequeue());
        StdOut.println(randomQ.dequeue());
        StdOut.println(randomQ.size());
        StdOut.println(randomQ.sample());
        StdOut.println(randomQ.sample());
        for (Integer n : randomQ) {
            StdOut.println(n);
        }
    }
}
