package russell.tests.algorithms.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import russell.tests.algorithms.domain.Palindrome;

@RunWith(JUnit4.class)
public class PalindromeTest {

    @Test
    public void testIsPalindrome() {
    	Assert.assertTrue(Palindrome.isPalindrome("a"));
    	Assert.assertTrue(Palindrome.isPalindrome("aa"));
    	Assert.assertTrue(Palindrome.isPalindrome("aba"));
    	Assert.assertTrue(Palindrome.isPalindrome("abba"));
    	Assert.assertTrue(Palindrome.isPalindrome("abcba"));
    }
    
    @Test
    public void testLongestPalindrome() {
    	Assert.assertEquals("abcba", Palindrome.longestPalindrome("dabcba"));
    	Assert.assertEquals("aaabaaa", Palindrome.longestPalindrome("daaabaaac"));
    }
    
    @Test
    public void tetPartitions() {
    	List<List<String>> partitions = Palindrome.partitions("babbabb");
    	for (List<String> partial : partitions) {
    		System.out.println(partial);
    	}
    }
}
