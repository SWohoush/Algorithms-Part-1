import java.nio.BufferUnderflowException;

public class ArrayStack<Item> {
    private Item[] s;
    int N = 0;

    public ArrayStack() {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (s.length == N) {
            resize(s.length * 2);
        }
        s[N++] = item;
    }

    private void resize(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            newArray[i] = s[i];
        }
        s = newArray;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    public int getSize() {
        return N;
    }
}
