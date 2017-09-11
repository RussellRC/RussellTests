package russell.tests.algorithms.practice;

/**
 * http://www.geeksforgeeks.org/build-lowest-number-by-removing-n-digits-from-a-given-number/
 */
public class LowestNumber {

    public static void main(String[] args) {
        
        System.out.println(lowestNumber("4325043", 3));
        System.out.println(lowestNumber("765028321", 5));
        System.out.println(lowestNumber("121198", 2));
        
    }

    private static String lowestNumber(String str, int removeChars) {
        final StringBuilder sb = new StringBuilder(str.length() - removeChars);
        lowestNumber(str, removeChars, sb);
        return sb.toString();
    }

    private static void lowestNumber(String str, int removeChars, StringBuilder result) {
        //System.out.println(str + "," + removeChars);
        
        if (removeChars == 0) {
            result.append(str);
            return;
        }
        if (str.length() <= removeChars) {
            return;
        }
        
        // find the smallest digit and its index
        int min = Character.digit(str.charAt(0), 10);
        int minIndex = 0;
        for (int i = 1; i <= removeChars; i++) {
            final int digit = Character.digit(str.charAt(i), 10);
            if (digit < min) {
                min = digit;
                minIndex = i;
            }
        }
        // append smallest digit and recurse
        result.append(min);
        lowestNumber(str.substring(minIndex+1), removeChars-minIndex, result);
    }
}
