package org.russell.practice.unsorted;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] b = { 0, 10, 15, 16, 17, 20, 25, 26, 27, 28, 29 };
        int[] a = new int[8 + b.length];
        a[0] = 1;
        a[1] = 5;
        a[2] = 11;
        a[3] = 13;
        a[4] = 18;
        a[5] = 21;
        a[6] = 22;
        a[7] = 30;

        int indexA = 7;
        int indexB = b.length - 1;
        int i = a.length - 1;
        while (i >= 0) {
            if (a[indexA] > b[indexB]) {
                a[i--] = a[indexA--];
            } else {
                a[i--] = b[indexB--];
            }
            if (indexA < 0 || indexB < 0) {
                break;
            }
        }
        if (indexA < 0) {
            while (indexB >= 0) {
                a[i--] = b[indexB--];
            }
        }
        if (indexB < 0) {
            while (indexA >= 0) {
                a[i--] = a[indexA--];
            }
        }

        System.out.println(Arrays.toString(a));
    }

}
