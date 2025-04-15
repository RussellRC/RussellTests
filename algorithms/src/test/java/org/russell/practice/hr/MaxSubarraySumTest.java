package org.russell.practice.hr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.russell.practice.unsorted.MaxSubarraySum;

public class MaxSubarraySumTest {

  @Test
  public void testFile() {
    final Scanner scan =
        new Scanner(
            MaxSubarraySumTest.class.getResourceAsStream("MaxSubarraySumTest/input01.txt"),
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

    final Scanner scanOut =
        new Scanner(
            MaxSubarraySumTest.class.getResourceAsStream("MaxSubarraySumTest/output01.txt"),
            StandardCharsets.UTF_8.toString());
    scanOut.useDelimiter("\\A");
    for (int[] array : cases) {
      final String result = MaxSubarraySum.maxSubarray(array);
      final String expected = scanOut.nextLine();
      assertTrue(expected.equals(result));
    }

    System.out.println("Success!");
  }
}
