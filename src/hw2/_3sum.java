package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class _3sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();

        while (sc.hasNext()) {
            a.add(sc.nextInt());
        }

        a.sort(Integer::compare);

        for (int i = 0; i < a.size(); i++) {

            int sum = -a.get(i);
            int l = 0, r = a.size() - 1;
            while (l < i && i <
                if (a.get(l) + a.get(r) < sum) l++;
                else if (a.get(l) + a.get(r) > sum) r--;
                else {
                    System.out.println(a.get(l++) + " " + (-sum) + " " + a.get(r--));
                }
            }
        }
    }
}

// cmd: java _2sum.java < "C:\Users\ADMIN\Documents\DSA\algs4-data\1Kints.txt"