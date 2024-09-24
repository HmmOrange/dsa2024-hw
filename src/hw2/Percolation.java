// package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final int gridSize;
    private int openSiteCount = 0;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufNoBottom;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        gridSize = n;

        grid = new boolean[n][n]; // true = open, false = close

        uf = new WeightedQuickUnionUF(n * n + 2);
        ufNoBottom = new WeightedQuickUnionUF(n * n + 1); // without
    }
    // Helper functions
    private boolean valid(int row, int col, int size) {
        return 1 <= row && row <= size && 1 <= col && col <= size;
    }

    private int map2DTo1D(int row, int col, int size) {
        return size * (row - 1) + col;
    }

    private void tryUnion(int row, int col, int adjRow, int adjCol) {
        if (valid(adjRow, adjCol, gridSize) && isOpen(adjRow, adjCol)) {
            uf.union(map2DTo1D(row, col, gridSize), map2DTo1D(adjRow, adjCol, gridSize));
            ufNoBottom.union(map2DTo1D(row, col, gridSize), map2DTo1D(adjRow, adjCol, gridSize));
        }
    }

    // Main API
    public void open(int row, int col) {
        if (!valid(row, col, gridSize)) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) return;

        grid[row - 1][col - 1] = true;
        openSiteCount++;

        // Connect open sites in UF
        tryUnion(row, col, row - 1, col);
        tryUnion(row, col, row + 1, col);
        tryUnion(row, col, row, col + 1);
        tryUnion(row, col, row, col - 1);

        // Connect top and bottom row sites to virtual sites 0 and n * n + 1
        if (row == 1) {
            uf.union(0, map2DTo1D(row, col, gridSize));
            ufNoBottom.union(0, map2DTo1D(row, col, gridSize));
        }
        if (row == gridSize) {
            uf.union(gridSize * gridSize + 1, map2DTo1D(row, col, gridSize));
        }
    }

    public boolean isOpen(int row, int col) {
        if (!valid(row, col, gridSize)) {
            throw new IllegalArgumentException();
        }

        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!valid(row, col, gridSize)) {
            throw new IllegalArgumentException();
        }

        return isOpen(row, col) && ufNoBottom.find(map2DTo1D(row, col, gridSize)) == ufNoBottom.find(0);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(gridSize * gridSize + 1);
    }

    public static void main(String[] args) {

    }
}
