package russell.tests.algorithms;


public class StringTest {

    public static void main(String[] args) {        
        //allPossibleSubstrings("word");
    	
    }

    public static void testIntern() {
    	String s1 = "Test";
        String s2 = "Test";
        String s3 = new String("Test");
        final String s4 = s3.intern();
        
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
    }
    
    public static void allPossibleSubstrings(String word) {
    	for (int i = 0; i < word.length(); i++) {
    		for (int j = i+1; j < word.length(); j++) {
    			System.out.println(word.substring(i, j+1));
    		}
    	}
    }
}