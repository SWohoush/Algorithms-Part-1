import java.nio.BufferUnderflowException;

public class ArrayQueue<Item> {
    private int N;
    private Item[] s;
    private int first;
    private int last;

    public ArrayQueue() {
        s = (Item[]) new Object[1];
        first = 0;
        last = 0;
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            copy[i] = s[first];
            first = (first + 1) % s.length;
        }
        last = N;
        first = 0;
        s = copy;
    }

    public void enqueue(Item item) {
        if (N == s.length - 1) resize(s.length * 2);
        s[last] = item;
        last = (last + 1) % s.length;
        N++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new BufferUnderflowException();
        Item item = s[first];
        s[first] = null;
        if (N == 1) {
            first = 0;
            last = 0;
        } else first = (first + 1) % s.length;
        N--;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }
    
}
