// package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] results;
    private final int trialCount;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        int gridSize = n * n;
        trialCount = trials;
        results = new double[trials];

        while (trials-- > 0) {
            // Pre-determine the orders for opening sites to avoid opening the same site multiple times
            int[] attemptOrder = new int[n * n];
            for (int i = 0; i < n * n; i++) {
                attemptOrder[i] = i + 1;
            }
            StdRandom.shuffle(attemptOrder);

            // Start trial
            Percolation p = new Percolation(n);
            int attempt = 0;
            while (!p.percolates()) {
                int row = (attemptOrder[attempt] - 1) / n + 1;
                int col = (attemptOrder[attempt] - 1) % n + 1;
                attempt++;

                p.open(row, col);
            }

            results[trials] = attempt * 1.0 / gridSize;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trialCount);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trialCount);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}

// cmd: java -cp "C:\Users\ADMIN\Documents\DSA\algs4.jar";. Percolationps.java n T