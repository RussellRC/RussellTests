package org.russell.algorithms.practice.unsorted;

import org.apache.commons.lang3.StringUtils;

/**
 * Given a mapping between numbers and alphabets . Find the number of ways to decode a sequence of numbers
 * @author russellrazo
 */
public class DecodeNumbers {

    public static void main(String[] args) {
        
        /*
         * eg: a - 21 b - 2 c - 54 d - 5 e -4 f-1
         * 2154
         * 1) ac
         * 2) ade
         * 3) bfc
         * 4) bfde
         * = 4 ways to decode 
         */
        final char[] alpha = {'a', 'b', 'c', 'd', 'e', 'f'};
        final String[] nums = {"21", "2", "54", "5", "4", "1"};
        final String str = "2154";
        
        System.out.println(decode(alpha, nums, str));
        
    }

    private static int decode(final char[] alpha, final String[] nums, final String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        
        int total = 0;
        for (final String num : nums) {
            if (str.length() < num.length()) {
                continue;
            }
            if (num.equals(str.substring(0, num.length()))) {
                total = 1 + decode(alpha, nums, str.substring(num.length()));
            }
        }
        
        return total;
    }
}
