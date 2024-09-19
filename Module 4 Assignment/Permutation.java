import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        if (args.length != 0) {
            RandomizedQueue<String> randomQ = new RandomizedQueue<>();
            int n = Integer.parseInt(args[0]);
            for (int i = 0; i < n; i++) {
                randomQ.enqueue(StdIn.readString());
            }
            for (String q : randomQ) {
                StdOut.println(randomQ.dequeue());
            }
        }
    }
}
