package org.russell.algorithms.practice.unsorted;

public class Permutations {

    
    public static void main(String[] args) {
        
        final StringBuilder sb = new StringBuilder("ABCD");
        permutations(sb, 0, sb.length()-1);
    }

    private static void permutations(final StringBuilder sb, final int start, final int end) {
        
        if (start == end) {
            System.out.println(sb);
            return;
        }
        
        for (int i = start; i <= end; i++) {
            swap(sb, start, i);
            permutations(sb, start+1, end);
            swap(sb, start, i); // return string to the previous state
        }
    }
    
    private static void swap(final StringBuilder sb, final int i, final int j) {
        if (i == j) {
            return;
        }
        char ch = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, ch);
    }
}
