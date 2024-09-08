import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String word = null;
        double count = 0;
        while (!StdIn.isEmpty()) {
            String currWord = StdIn.readString();
            count++;
            if (StdRandom.bernoulli(1 / count)) {
                word = currWord;
            }
        }
        StdOut.println(word);
    }
}