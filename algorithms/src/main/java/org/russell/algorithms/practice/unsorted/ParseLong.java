package org.russell.algorithms.practice.unsorted;

import java.util.Random;

public class ParseLong {

    public static void main(final String[] args) {
        // Test min and max work
        System.out.println(Long.MIN_VALUE);
        System.out.println(parseLong(String.valueOf(Long.MIN_VALUE)));
        System.out.println("===");
        System.out.println(Long.MAX_VALUE);
        System.out.println(parseLong(String.valueOf(Long.MAX_VALUE)));
        System.out.println("===");
        
        // Test 10k random numbers
        Random r = new Random();
        int i = 10000;
        while (i > 0) {
            final long nextLong = r.nextLong();
            if (nextLong != parseLong(String.valueOf(nextLong))) {
                System.out.println("ERROR!!!  " + nextLong);
            }
            i--;
        }
        
        System.out.println("sucess");
    }
    
    public static long parseLong(final String s) {
        final boolean isNegative = s.charAt(0) == '-';
        int index = isNegative ? 1 : 0;
        
        long result = 0;
        while (index < s.length()) {
            if (result * 10 > 0) {
                throw new NumberFormatException("Number out of range");
            }
            result = result * 10;
            final int digit = Character.getNumericValue(s.charAt(index));
            if (result - digit > 0) {
                throw new NumberFormatException("Number out of range"); 
            }
            result = result - digit;
            
            index++;
        }
        
        if (!isNegative && -result < 0) {
            // End up in positive MIN_VALUE
            throw new NumberFormatException("Number out of range");
        }
        
        return isNegative ? result : -result;
    }
    
}
