import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF fullCheck;
    private boolean[][] squares;
    private int openSites = 0;
    private final int numObjects;
    private final int totalSquares;
    private final int topSite;
    private final int bottomSite;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        totalSquares = n * n;
        topSite = totalSquares;
        bottomSite = topSite + 1;
        numObjects = n;
        sites = new WeightedQuickUnionUF(totalSquares + 2);
        fullCheck = new WeightedQuickUnionUF(totalSquares + 1);
        squares = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                squares[i][j] = false;
            }
        }
        for (int i = 0; i < n; i++) {
            sites.union(topSite, i);
            sites.union(bottomSite, totalSquares - 1 - i);
        }
    }

    private boolean checkRange(int row, int col) {
        if (numObjects < row || row <= 0 || numObjects < col || col <= 0)
            return false;
        else
            return true;
    }

    public void open(int row, int col) {
        if (numObjects < row || row <= 0 || numObjects < col || col <= 0) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            squares[row - 1][col - 1] = true;
            openSites++;
            int square = (numObjects * (row - 1)) + (col - 1);
            if (row == 1) {
                fullCheck.union(topSite, square);
            }
            if (checkRange(row, col - 1)) {
                if (isOpen(row, col - 1)) {
                    sites.union(square, square - 1);
                    fullCheck.union(square, square - 1);
                }
            }
            if (checkRange(row, col + 1)) {
                if (isOpen(row, col + 1)) {
                    sites.union(square, square + 1);
                    fullCheck.union(square, square + 1);
                }
            }
            if (checkRange(row + 1, col)) {
                if (isOpen(row + 1, col)) {
                    sites.union(square, square + squares[0].length);
                    fullCheck.union(square, square + squares[0].length);
                }
            }
            if (checkRange(row - 1, col)) {
                if (isOpen(row - 1, col)) {
                    sites.union(square, square - squares[0].length);
                    fullCheck.union(square, square - squares[0].length);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > numObjects || row <= 0 || col > numObjects || col <= 0) {
            throw new IllegalArgumentException();
        }
        return squares[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row > numObjects || row <= 0 || col > numObjects || col <= 0) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            int square = (numObjects * (row - 1)) + col - 1;
            return fullCheck.find(square) == fullCheck.find(topSite);
        } else return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        if (numObjects == 1) {
            return isOpen(1, 1);
        }
        return sites.find(topSite) == sites.find(bottomSite);
    }
}
