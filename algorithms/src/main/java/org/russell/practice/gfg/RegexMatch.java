package org.russell.practice.gfg;

/**
 * http://www.geeksforgeeks.org/wildcard-character-matching/
 */
public class RegexMatch {

    public static void main(String[] args) {
        System.out.println(match("?", "x"));
        System.out.println(match("*", "x"));
        System.out.println(match("*", "xyz"));
        
        System.out.println(match("*aab?c", "dbvaabcc"));
        System.out.println(match("*c*", "ca"));
        System.out.println(match("?c?", "ca"));
        
        System.out.println(match("g*ks", "geeks")); // Yes
        System.out.println(match("ge?ks*", "geeksforgeeks")); // Yes
        System.out.println(match("g*k", "gee")); // No because 'k' is not in second
        System.out.println(match("*pqrs", "pqrst")); // No because 't' is not in first
        System.out.println(match("abc*bcd", "abcdhghgbcd")); // Yes
        System.out.println(match("abc*c?d", "abcd")); // No because second must have 2 instances of 'c'
        System.out.println(match("*c*d", "abcd")); // Yes
        System.out.println(match("*?c*d", "abcd")); // Yes
    }

    private static boolean match(final String regex, final String str) {
        if (regex.isEmpty() && str.isEmpty()) {
            return true;
        }
        
        if (regex.length() > 1 && regex.charAt(0) == '*' && str.isEmpty()) {
            return false;
        }
        
        if ((!regex.isEmpty() && regex.charAt(0) == '?' && !str.isEmpty())
            || (!regex.isEmpty() && !str.isEmpty() && regex.charAt(0) == str.charAt(0))) {
            return match(regex.substring(1), str.substring(1));
        }
        
        if (!regex.isEmpty() && regex.charAt(0) == '*') {
            return match(regex.substring(1), str) || match(regex, str.substring(1));
        }
        
        return false;
    }
    
}
