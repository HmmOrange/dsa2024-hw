package hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class _2sum {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<Integer>();

        while (sc.hasNext()) {
            a.add(sc.nextInt());
        }

        a.sort(Integer::compare);

//        for (Integer i: a) {
//            System.out.println(i);
//        }

        int l = 0, r = a.size() - 1;
        while (l <= r){
            if (a.get(l) + a.get(r) < 0) l++;
            else if (a.get(l) + a.get(r) > 0) r--;
            else {
                System.out.println(a.get(l++) + " " + a.get(r--));
            }
        }

//        for (int i = 0; i < a.size(); i++) {
//            for (int j = 0; j < a.size(); j++) {
//                if (a.get(i) + a.get(j) == 0) System.out.println(i + " " + j);
//            }
//        }
    }
}

// cmd: java _2sum.java < "C:\Users\ADMIN\Documents\DSA\algs4-data\1Kints.txt"