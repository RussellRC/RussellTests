package russell.tests.algorithms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PalindromeTest {

    Palindrome palindrome = new Palindrome();
    
    @Test
    public void shouldNotBePalindrome() {
        // null should not be palindrome
        Assert.assertFalse(palindrome.isPalindrome(null));
        
        // empty string should not be palindrome 
        Assert.assertFalse(palindrome.isPalindrome(""));
        Assert.assertFalse(palindrome.isPalindrome("   "));
        
        // one letter should not be palindrome
        Assert.assertFalse(palindrome.isPalindrome("p"));
        Assert.assertFalse(palindrome.isPalindrome("  p    "));
        
        Assert.assertFalse(palindrome.isPalindrome("pedrep"));
    }
    
    @Test
    public void shouldBePalindrome() {
        // even word
        Assert.assertTrue(palindrome.isPalindrome("anna"));
        
        // odd word
        Assert.assertTrue(palindrome.isPalindrome("ana"));
        
        // case sensitive
        Assert.assertTrue(palindrome.isPalindrome("Anna"));
        
        // leading and trailing spaces
        Assert.assertTrue(palindrome.isPalindrome(" Anna    "));
    }
    
}
