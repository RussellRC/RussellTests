package org.russell.dsa.other;

import org.junit.jupiter.api.Test;
import org.russell.practice.unsorted.Palindrome;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromeTest {

    @Test
    public void testIsPalindrome() {
    	assertTrue(Palindrome.isPalindrome("a"));
    	assertTrue(Palindrome.isPalindrome("aa"));
    	assertTrue(Palindrome.isPalindrome("aba"));
    	assertTrue(Palindrome.isPalindrome("abba"));
    	assertTrue(Palindrome.isPalindrome("abcba"));
    }
    
    @Test
    public void testLongestPalindrome() {
    	assertEquals("abcba", Palindrome.longestPalindrome("dabcba"));
    	assertEquals("aaabaaa", Palindrome.longestPalindrome("daaabaaac"));
		assertEquals("aaaaaa", Palindrome.longestPalindrome("daaaaaac"));
    }
    
    @Test
    public void tetPartitions() {
    	List<List<String>> partitions = Palindrome.partitions("babbabb");
    	for (List<String> partial : partitions) {
    		System.out.println(partial);
    	}
    }
}
