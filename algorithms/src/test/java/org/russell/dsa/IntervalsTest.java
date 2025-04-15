package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalsTest {

  private Intervals inv;

  @BeforeEach
  void beforeEach() {
    inv = new Intervals();
  }

  @Test
  void canAttendMeetings() {
    assertFalse(inv.canAttendMeetings(new int[][] {{1, 5}, {3, 9}, {6, 8}}));
    assertTrue(inv.canAttendMeetings(new int[][] {{10, 12}, {6, 9}, {12, 15}}));
  }

  @Test
  void insert() {
    int[][] intervals, expected;
    int[] newInterval;

    intervals = new int[][] {{1, 3}, {6, 9}};
    newInterval = new int[] {2, 5};
    expected = new int[][] {{1, 5}, {6, 9}};
    assertArrayEquals(expected, inv.insert(intervals, newInterval));

    intervals = new int[][]{{1,2}, {3,5}, {6,7}, {8,10}, {12,16}};
    newInterval = new int[]{4, 8};
    expected = new int[][]{{1,2}, {3,10}, {12,16}};
    assertArrayEquals(expected, inv.insert(intervals, newInterval));
  }

  @Test
  void mergeIntervals() {
    assertArrayEquals(new int[][]{{1,5}, {6,9}}, inv.mergeIntervals(new int[][]{{3,5}, {1,4}, {7,9}, {6,8}}));
  }

  @Test
  void employeeFreeTime() {
    int[][] expected = new int[][]{{5,6}};
    int[][][] schedule = new int[][][]{
            {{2,4}, {7,10}},
            {{1,5}},
            {{6,9}}
    };

    assertArrayEquals(expected, inv.employeeFreeTime(schedule));
  }
}
