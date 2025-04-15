package org.russell.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {

  /** https://www.hellointerview.com/learn/code/two-pointers/container-with-most-water */
  public int maxArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }

    int maxArea = 0;
    int left = 0;
    int right = heights.length - 1;

    while (left < right) {
       int width = right - left;
       int height = Math.min(heights[left], heights[right]);
       int area = width * height;
       maxArea = Math.max(area, maxArea);
       if (heights[left] < heights[right]) {
         left++;
       } else {
         right--;
       }
    }

    return maxArea;
  }

  /** https://www.hellointerview.com/learn/code/two-pointers/3-sum */
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    final List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        } else {
          result.add(List.of(nums[i], nums[left], nums[right]));
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }
          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }
          left++;
          right--;
        }
      }
    }

    return result;
  }

  /** https://www.hellointerview.com/learn/code/two-pointers/valid-triangle-number */
  public int triangleNumbers(int[] nums) {
    Arrays.sort(nums);

    int count = 0;
    for (int i = nums.length - 1; i >= 2; i--) {
      int left = 0;
      int right = i - 1;
      while (left < right) {
        if (nums[left] + nums[right] > nums[i]) {
          count = count + (right - left);
          right--;
        } else {
          left++;
        }
      }
    }

    return count;
  }

  /** https://www.hellointerview.com/learn/code/two-pointers/move-zeroes */
  public int[] moveZeroes(final int[] nums) {
    int nextNonZero = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int temp = nums[nextNonZero];
        nums[nextNonZero] = nums[i];
        nums[i] = temp;
        nextNonZero++;
      }
    }
    return nums;
  }

  /** https://www.hellointerview.com/learn/code/two-pointers/sort-colors */
  public int[] sortColors(final int[] nums) {
    int left = 0;
    int right = nums.length-1;
    int i = 0;

    while (i <= right) {
      if (nums[i] == 0) {
        swap(nums, left, i);
        left++;
        i++;
      } else if (nums[i] == 2) {
        swap(nums, i, right);
        right--;
      } else {
        i++;
      }
    }

    return nums;
  }

  private static void swap(final int[] nums, final int a, final int b) {
    final int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }

  /** https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water */
  public int trappingWatter(int[] heights) {
    int left = 0, right = heights.length - 1;
    int leftMax = heights[left], rightMax = heights[right];
    int total = 0;

    while (left + 1 < right) {
      if (rightMax > leftMax) {
        left++;
        leftMax = Math.max(heights[left], leftMax);
        if (heights[left] < leftMax) {
          // water trapped
          final int water = leftMax - heights[left];
          total = total + water;
        }
      } else {
        right--;
        rightMax = Math.max(heights[right], rightMax);
        if (heights[right] < rightMax) {
          // water trapped
          final int water = rightMax - heights[right];
          total = total + water;
        }
      }
    }

    return total;
  }
}
