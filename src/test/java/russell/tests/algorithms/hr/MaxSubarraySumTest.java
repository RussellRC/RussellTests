package russell.tests.algorithms.hr;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.Assert;

import russell.tests.algorithms.practice.MaxSubarraySum;

@RunWith(JUnit4.class)
public class MaxSubarraySumTest {

    @Test
    public void testFile() {
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
            final String result = MaxSubarraySum.maxSubarray(array);
            final String expected = scanOut.nextLine();
            Assert.isTrue(expected.equals(result));
        }
        
        System.out.println("Success!");
    }
}
