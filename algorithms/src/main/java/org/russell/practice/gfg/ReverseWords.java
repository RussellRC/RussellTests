package org.russell.practice.gfg;

/**
 * http://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 */
public class ReverseWords {

    public static void main(String[] args) {
        
        String s = "getting good at coding needs a lot of practice";
        System.out.println(reverseWords(s));
        
    }

    private static String reverseWords(final String s) {
        final StringBuilder result = new StringBuilder(s.length());
        
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                result.append(reverse(word)).append(' ');
                word = new StringBuilder();
            } else {
                word.append(s.charAt(i));
            }
        }
        result.append(reverse(word));
        
        return reverse(result);
    }

    /**
     * Reverse one single string in n/2 time => O(n)
     * @param s
     * @return
     */
    private static String reverse(final CharSequence s) {
        final char[] rev = new char[s.length()];
        
        for (int i = (s.length()-1) / 2; i >= 0; i--) {
            int j = s.length() - 1 - i;
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            rev[j] = ci;
            rev[i] = cj;
        }
        
        return new String(rev);
    }
    
    
}
