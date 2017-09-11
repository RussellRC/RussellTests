package russell.tests.algorithms.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Input: number (non-negative integer), dictionary (keys are single digit 0-9)
// Output: print all possible ways to decode the input number using the dictionary

// 34, [1 => [], 3 => [A, B, C], 4 => [Q, R, T], 7 => [P, Q, R, S], 0 => [X, Y, Z]]
// AQ
// BQ
// CT

// 2471, [1 => [], 3 => [A, B, C], 4 => [Q, R, T], 7 => [P, Q, R, S], 0 => [X, Y, Z]]
// QP
// QS

// 0, [1 => [], 3 => [A, B, C], 4 => [Q, R, T], 7 => [P, Q, R, S], 0 => [X, Y, Z]]
// X
// Y
// Z
public class DecodeNumbers2 {
    
    public static void main(String[] args) {
        
        final Map<Character, List<Character>> dict = new LinkedHashMap<>();
        dict.put('1', Collections.emptyList());
        dict.put('3', Arrays.asList('A', 'B', 'C'));
        dict.put('4', Arrays.asList('Q', 'R', 'T'));
        dict.put('7', Arrays.asList('P', 'Q', 'R', 'S'));
        dict.put('0', Arrays.asList('X', 'Y', 'Z'));
        
        decode("3141", dict, new StringBuilder());
    }

    private static void decode(String string, final Map<Character, List<Character>> dict, final StringBuilder sb) {
        
        if (string.isEmpty()) {
            System.out.println(sb);
            return;
        }
        
        final Character k = string.charAt(0);
        string = string.substring(1);
        final List<Character> list = dict.get(k);
        
        if (list == null || list.isEmpty()) {
            decode(string, dict, sb);
        } else {
            for (final Character c : list) {
                sb.append(c);
                decode(string, dict, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}