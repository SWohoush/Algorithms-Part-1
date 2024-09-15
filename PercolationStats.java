import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] trialResults;
    private final double CONFIDENCE_CONST = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        trialResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            int openSites = 0;
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int a = StdRandom.uniformInt(1, n + 1);
                int b = StdRandom.uniformInt(1, n + 1);
                if (!percolation.isOpen(a, b)) {
                    percolation.open(a, b);
                    openSites++;
                }
            }
            trialResults[i] = (double) openSites / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(trialResults);
    }

    public double stddev() {
        return StdStats.stddev(trialResults);
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_CONST * stddev()) / Math.sqrt(trialResults.length));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_CONST * stddev()) / Math.sqrt(trialResults.length));
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            StdOut.println("mean =" + ps.mean());
            StdOut.println("stddev =" + ps.stddev());
            StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
        }
    }
}
