package hw2;

import edu.princeton.cs.algs4.*;

public class UFClient2 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int stepCount = 0, connectionCount = 0;
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            stepCount++;
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connectionCount++;
            }
            if (N == connectionCount + 1) {
                System.out.println(stepCount);
                System.exit(0);
            }
        }
        System.out.println("FAILED");
    }
}

// cmd: java -cp "C:\Users\ADMIN\Documents\DSA\algs4.jar";. UFClient2.java < "C:\Users\ADMIN\Documents\DSA\algs4-data\tinyUF.txt"