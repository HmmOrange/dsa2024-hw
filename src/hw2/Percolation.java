package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int[][] grid;
    private int gridSize = n;
    private int openSiteCount = 0;
    private int[] hx = {1, -1, 0, 0};
    private int[] hy = {0, 0, 1, -1};
    private WeightedQuickUnionUF uf;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        gridSize = n;

        grid = new int[n][n]; // 0 = open, 1 = close
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = 1;
            }
        }

        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        if (1 > row || row > gridSize || 1 > col || col > gridSize) {
            throw new IllegalArgumentException();
        }

        grid[row][col] = 0;
        openSiteCount++;

        // Connect open sites in UF
        for (int i = 0; i < 4; i++) {
            int fx = row + hx[i];
            int fy = col + hy[i];

            if (1 <= fx && fx <= gridSize && 1 <= fy && fy <= gridSize && isOpen(fx, fy)) {
                uf.union(gridSize * (row - 1) + col, gridSize * (fx - 1) + fy);
            }
        }

        // Connect top and bottom row sites to 2 virtual sites 0 and n*n + 1
        if (row == 1) {
            uf.union(0, col);
        }

        if (row == gridSize) {
            uf.union(gridSize * (row - 1) + col, gridSize * gridSize + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        if (1 > row || row > gridSize || 1 > col || col > gridSize) {
            throw new IllegalArgumentException();
        }

        return grid[row][col] == 0;
    }

    public boolean isFull(int row, int col) {
        if (1 > row || row > gridSize || 1 > col || col > gridSize) {
            throw new IllegalArgumentException();
        }

        return grid[row][col] == 1;
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
