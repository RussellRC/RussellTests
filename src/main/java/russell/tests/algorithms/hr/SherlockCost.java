package russell.tests.algorithms.hr;

import java.util.Scanner;

/**
 * This doesn't have much to do with Sherlock
 * 
 * https://www.hackerrank.com/challenges/sherlock-and-cost
 */
public class SherlockCost {

    public static void main(String[] args) {

        final Scanner s = new Scanner(System.in);

        final int testCases = s.nextInt();

        for (int testCase = 0; testCase < testCases; testCase++) {
            final int size = s.nextInt();

            if (size == 1) {
                long l = s.nextLong();
                System.out.println(0);
                continue;
            }

            final long[] b = new long[size];
            long sum1 = 0l; // Start at 1
            long sum2 = 0l; // Start at B[i]

            for (int i = 0; i < size; i++) {
                b[i] = s.nextLong();

                if (i > 0) {
                    long loHi = Math.abs(b[i] - 1l);
                    long hiLo = Math.abs(b[i - 1] - 1l);
                    long hiHi = Math.abs(b[i] - b[i - 1]);

                    long s1 = Math.max(sum1, sum2 + hiLo);
                    long s2 = Math.max(sum1 + loHi, sum2 + hiHi);
                    sum1 = s1;
                    sum2 = s2;
                }
            }
            System.out.println(Math.max(sum1, sum2));
        }
    }

}
