package russell.tests.algorithms.hr;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.util.Assert;

public class MaxSubarray {

    public static void main(String[] args) {
        final Scanner scan = new Scanner(Thread.currentThread().getContextClassLoader()
                                               .getResourceAsStream("russell/tests/algorithms/hr/MaxSubarrayResources/input01.txt"),
                                         StandardCharsets.UTF_8.toString());
        scan.useDelimiter("\\A");
        final int numCases = Integer.parseInt(scan.nextLine());
        
        final List<int[]> cases = new ArrayList<>();
        for (int i = 0; i < numCases; i++) {
            scan.nextLine();
            final String[] strArr = scan.nextLine().split("\\s+");
            final int[] intArr = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();
            cases.add(intArr);
        }
        
        
        final Scanner scanOut = new Scanner(Thread.currentThread().getContextClassLoader()
                                                  .getResourceAsStream("russell/tests/algorithms/hr/MaxSubarrayResources/output01.txt"),
                                            StandardCharsets.UTF_8.toString());
        scanOut.useDelimiter("\\A");
        for (int[] array : cases) {
            final String result = maxSubarray(array);
            final String expected = scanOut.nextLine();
            
            Assert.isTrue(expected.equals(result));
        }
        
        System.out.println("Success!");
    }
    
    
    private static String maxSubarray(final int[] array) {
        final int maxNonCont = maxNonCont(array);
        final int maxCont = maxCont(array);
        return String.format("%d %d", maxCont, maxNonCont);
    }
    
    private static int maxCont(final int[] array) {
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
    
    /**
     * First approach to max continuous sub-array
     * Throws OOM on big arrays.  Use under your own risk
     * @param array
     * @return
     */
    private static int maxCont_OOM(final int[] array) {
        int greaterSingle = Integer.MIN_VALUE;
        final int[][] matrix = new int[array.length][array.length];
        
        // init
        for (int i = 0; i < array.length; i++) {
            matrix[0][i] = array[i];
            greaterSingle = array[i] > greaterSingle ? array[i] : greaterSingle;
        }
        
        int maxCont = matrix[0][0];
        for (int y = 1; y < array.length; y++) {
            for (int x = 1; x < array.length; x++) {
                matrix[y][x] = matrix[0][x] + matrix[y-1][x-1];
                maxCont = matrix[y][x] > maxCont ? matrix[y][x] : maxCont;
            }
        }
        maxCont = maxCont > greaterSingle ? maxCont : greaterSingle;
        return maxCont;
    }
}