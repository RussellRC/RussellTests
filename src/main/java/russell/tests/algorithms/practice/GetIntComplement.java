package russell.tests.algorithms.practice;

/**
 * Get bitwise complement of a number
 * Example: Input: 10 (1010) Output: 5 (0101)
 */
public class GetIntComplement {

    public static void main(String[] args) {
        
        System.out.println(getIntComplement(10));
    }

    private static int getIntComplement(int num) {
        final String binNum = Integer.toBinaryString(num);
        
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binNum.length(); i++) {
            if (binNum.charAt(i) == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        
        return Integer.parseInt(sb.toString(), 2);
    }
    
}
