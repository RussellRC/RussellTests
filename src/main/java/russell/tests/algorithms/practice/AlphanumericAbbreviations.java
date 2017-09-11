package russell.tests.algorithms.practice;

/**
 * Alphanumeric Abbreviations of a String
 * Given a string of characters of length less than 10.
 * We need to print all the alpha-numeric abbreviation of the string.
 * http://www.geeksforgeeks.org/alphanumeric-abbreviations-of-a-string/
 * @author russellrazo
 *
 */
public class AlphanumericAbbreviations {

    
    public static void main(String[] args) {
        
        alphanumAbbreviations("ANKS");
        
    }
    
    private static void alphanumAbbreviations(final String source) {
        alphanumAbbreviations(source, new StringBuilder(), 0);
    }

    private static void alphanumAbbreviations(final String source, final StringBuilder sb, final int index) {
        if (index == source.length()) {
            System.out.println(sb);
            return;
        }
        
        // case1: append the next character and recurse
        sb.append(source.charAt(index));
        alphanumAbbreviations(source, sb, index+1);
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        
        // case2: replace last character for count and recurse
        if (sb.length() != 0) {
            final char last = sb.charAt(sb.length()-1);
            if (Character.isDigit(last)) {
                int count = Character.digit(last, 10) + 1;
                sb.setCharAt(sb.length()-1, Character.forDigit(count, 10));
            } else {
                sb.append('1');
            }
        } else {
            sb.append('1');
        }
        alphanumAbbreviations(source, sb, index+1);
        
        // end recursion"
        // if last character is digit, decrease count
        // otherwise remove it
        if (sb.length() != 0) {
            final char last = sb.charAt(sb.length()-1);
            if (Character.isDigit(last) && last != '1') {
                int count = Character.digit(last, 10) - 1;
                sb.setCharAt(sb.length()-1, Character.forDigit(count, 10));
            } else {
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    
}
