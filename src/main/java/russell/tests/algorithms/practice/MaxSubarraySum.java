package russell.tests.algorithms.practice;

/**
 * https://www.hackerrank.com/challenges/maxsubarray
 */
public class MaxSubarraySum {

    
    public static void main(String[] args) {
        
        int[] arr = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
        System.out.println(maxCont(arr));
        System.out.println(maxNonCont(arr));
        
    }
    
    public static String maxSubarray(final int[] array) {
        final int maxNonCont = maxNonCont(array);
        final int maxCont = maxCont(array);
        return String.format("%d %d", maxCont, maxNonCont);
    }
    
    public static int maxCont(final int[] array) {
        int maxCont = array[0];
        int maxPos = array[0];
        for (int i = 1; i < array.length; i++) {
            maxPos = Math.max(array[i], maxPos + array[i]);
            maxCont = Math.max(maxCont, maxPos);
        }
        return maxCont;
    }
    
    private static int maxNonCont(final int[] array) {
        int maxNonCont = Integer.MIN_VALUE;
        int greaterNeg = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                greaterNeg = array[i] > greaterNeg ? array[i] : greaterNeg; 
            } else {
                if (maxNonCont == Integer.MIN_VALUE) {
                    maxNonCont = array[i];
                } else {
                    maxNonCont = maxNonCont + array[i];
                }
            }
        }
        maxNonCont = maxNonCont > greaterNeg ? maxNonCont : greaterNeg;
        return maxNonCont;
    }
    
}