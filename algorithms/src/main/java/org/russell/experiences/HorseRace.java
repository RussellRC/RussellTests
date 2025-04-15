package org.russell.experiences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
Horse Racing
Chief A and Lord B each have N horses that will race against each other.
In each round, one horse from A and one from B will compete in a 1:1 race.
There will be a total of N rounds, with each horse racing once.
Each horse's speed is represented by an integer, and no two horses have the same speed.
A horse with a higher speed will always defeat a horse with a lower speed.
Given two arrays of integers, A[N] and B[N], representing the speeds of A’s and B’s horses respectively,
the horses will enter the race according to their order in the arrays, meaning horse A[i] will race against horse B[i].
The objective is to rearrange array B[N] to maximize the number of rounds that Lord B wins,
and return the rearranged array B[N] along with the total number of winning rounds for B.
Note that there may be multiple arrangements of B[N] that achieve the same maximum number of wins.
Example:
Input:    A[N]=[50,25,99,40,90],B[N]=[35,10,60,30,95]
Output:NewB[N]=[95,30,10,60,35],wins=3

25 40 50 90 99
10 30 35 60 95

95 -> 90
60 -> 50
35 -> 25
30 -> 99
10 -> 40

[60, 35, 0, 0, 95]

time = O(n + 2nlogn + n + n)
space = O(n)
*/
public class HorseRace {

    public static void main(String[] args) {

        final int[] a = new int[]{50, 25, 99, 40, 90};
        final int[] b = new int[]{35, 10, 60, 30, 95};
        final int[] newB = raceHorses(a, b);
        System.out.println(Arrays.toString(newB));
    }

    public static int[] raceHorses(int[] a, int[] b) {
        final Map<Integer, Integer> indexesA = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            indexesA.put(a[i], i);
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int[] newB = new int[b.length];

        int beatableA = a.length-1;
        int lastB = b.length - 1;
        int wins = 0;

        for (int i = b.length - 1; i >= 0; i--) {
            for (int j = beatableA; j >= 0; j--) {
                if (b[i] > a[j]) {
                    beatableA = j-1;
                    final int newIndex = indexesA.get(a[j]);
                    newB[newIndex] = b[i];
                    lastB--;
                    wins++;
                    break;
                }
            }
        }

        int i = 0;
        while (lastB >= 0) {
            for (; i < newB.length; i++) {
                if (newB[i] == 0) {
                    newB[i] = b[lastB];
                    lastB--;
                    break;
                }
            }
        }

        return newB;
    }
}
