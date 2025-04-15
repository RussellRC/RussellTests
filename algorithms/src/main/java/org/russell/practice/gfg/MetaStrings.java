package org.russell.practice.gfg;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings, the task is to check whether these strings are meta strings or not.
 * Meta strings are the strings which can be made equal by exactly one swap in any of the strings.
 * Equal string are not considered here as Meta strings.
 * 
 * http://www.geeksforgeeks.org/meta-strings-check-two-strings-can-become-swap-one-string/
 */
public class MetaStrings {

    public static void main(String[] args) {
        
        System.out.println(isMetaStrings("geeks", "keegs"));
        System.out.println(isMetaStrings("rsting", "string"));
        System.out.println(isMetaStrings("converse", "conserve"));
    }

    private static boolean isMetaStrings(final String s1, final String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // indexes of unmatched chars
        final List<Integer> unmatched = new ArrayList<>();
        
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                unmatched.add(i);
                
                // no meta if there are more than 2 missmatches
                if (unmatched.size() > 2) {
                    return false;
                }
            }
        }
        
        // equal strings are not meta
        if (unmatched.isEmpty()) {
            return false;
        }
        
        if (s1.charAt(unmatched.get(0)) == s2.charAt(unmatched.get(1)) && s1.charAt(unmatched.get(1)) == s2.charAt(unmatched.get(0))) {
            return true;
        }
        
        return false;
    }
    
}
