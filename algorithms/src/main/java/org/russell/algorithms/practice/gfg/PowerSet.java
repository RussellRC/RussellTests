package org.russell.algorithms.practice.gfg;

/**
 * http://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
 * 
 */
public class PowerSet {

    public static void main(String[] args) {
        powerSet(new String[] {"a", "b", "c", "d"});
    }

    private static <T> void powerSet(T[] array) {
        int numSubset = (1 << array.length); // there are 2^length subsets
        for (int i = 0; i < numSubset; i++) {
            
            for (int j = 0; j < array.length; j++) {
                final int pow = (int) Math.pow(2, j); // same as (1 << j)
                if ((i & pow) > 0) {
                    System.out.print(array[j] + " ");
                }
            }
            System.out.println();
        }
        
    }
}
