package org.russell.algorithms.practice.gfg;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/longest-possible-chunked-palindrome/
 * Given a string, the task is to return the length of its longest possible chunked palindrome.
 * It means a palindrome formed by substring in the case when it is not formed by characters of the string.
 *
 */
public class LongestChunkedPalindrome {

    public static void main(String[] args) {
        final String str = "ghiabcdefhelloadamhelloabcdefghi";
        System.out.println(str.length());
        System.out.println(lcp(str));
    }

    private static List<String> lcp(final String str) {
        final List<String> result = new ArrayList<>();
        lcp(new StringBuilder(str), 0, str.length()-1, result);
        return result;
    }
    
    private static void lcp(final StringBuilder str, int start, int end, final List<String> result) {
        if (start > end || str == null || str.length() == 0) {
            return;
        }
        
        if (str.length() == 1) {
            result.add(str.toString());
        }
        
        final StringBuilder sb1 = new StringBuilder();
        final StringBuilder sb2 = new StringBuilder();
        while (start < end) {
            
            if (start + 1 == end) {
                // 1 character left
                sb1.append(str.charAt(start)).append(str.charAt(end)).append(sb2);
                result.add(sb1.toString());
                return;
            }
            
            // sb1 and sb2 are same, add them to result and recurse
            if (sb1.length() > 0 && sb1.toString().equals(sb2.toString())) {
                result.add(sb1.toString());
                result.add(sb2.toString());
                lcp(str, start, end, result);
                return;
            }
            
            // keep adding characters until sb1 and sb2 are same
            sb1.append(str.charAt(start));
            sb2.insert(0, str.charAt(end));
            str.deleteCharAt(end);
            str.deleteCharAt(start);
            start = 0;
            end = str.length()-1;
        }
    }
}
