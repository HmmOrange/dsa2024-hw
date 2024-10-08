package hw2;

import edu.princeton.cs.algs4.*;

public class UFClient1 {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}

// cmd: java -cp "C:\Users\ADMIN\Documents\DSA\algs4.jar";. UFClient1.java < "C:\Users\ADMIN\Documents\DSA\algs4-data\tinyUF.txt"

