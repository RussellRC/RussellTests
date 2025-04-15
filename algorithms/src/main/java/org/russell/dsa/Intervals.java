package org.russell.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Intervals {

  /** https://www.hellointerview.com/learn/code/intervals/can-attend-meetings */
  boolean canAttendMeetings(int[][] meetings) {
    if (meetings.length == 1) {
      return true;
    }

    final List<Interval> intervals =
        Arrays.stream(meetings)
            .sorted(Comparator.comparingInt(arr -> arr[0]))
            .map(arr -> new Interval(arr[0], arr[1]))
            .toList();

    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).start < intervals.get(i - 1).end) {
        return false;
      }
    }

    return true;
  }

  /** https://www.hellointerview.com/learn/code/intervals/insert-interval */
  public int[][] insert(int[][] intervals, int[] newInterval) {

    final List<int[]> result = new ArrayList<>();
    int[] merged = Arrays.copyOf(newInterval, newInterval.length);

    int i;
    boolean inserted = false;
    for (i = 0; i < intervals.length; i++) {
      final var current = intervals[i];

      if (current[1] < merged[0]) {
        // current ends before new
        result.add(current);
      } else if (current[0] > merged[1]) {
        // current starts after new
        if (!inserted) {
          result.add(merged);
          inserted = true;
        }
        result.add(current);
      } else {
        // current overlaps with new, so merge
        merged = new int[] {Math.min(current[0], merged[0]), Math.max(current[1], merged[1])};
      }
    }

    if (!inserted) {
      result.add(merged);
    }

    return result.toArray(new int[result.size()][]);
  }

  /** https://www.hellointerview.com/learn/code/intervals/non-overlapping-intervals */
  int nonOverlappingIntervals(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    // Sort by end time
    Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));

    int end = intervals[0][1];
    int nonOverlappingIntervals = 1;

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] >= end) {
        // current is non-overlapping
        end = intervals[i][1];
        nonOverlappingIntervals++;
      }
    }

    return intervals.length - nonOverlappingIntervals;
  }

  /** https://hellointerview.com/learn/code/intervals/merge-intervals */
  int[][] mergeIntervals(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
    final var result = new ArrayList<int[]>();

    for (final var interval : intervals) {
      if (result.isEmpty() || interval[0] > result.getLast()[1]) {
        result.add(interval);
      } else {
        result.getLast()[1] = Math.max(result.getLast()[1], interval[1]);
      }
    }

    return result.toArray(new int[result.size()][]);
  }

  /** https://www.hellointerview.com/learn/code/intervals/employee-free-time */
  int[][] employeeFreeTime(int[][][] schedule) {
    // Sort all schedules sorted by start time
    final List<int[]> flatSchedule =
        Arrays.stream(schedule)
            .flatMap(Arrays::stream)
            .sorted(Comparator.comparingInt(interval -> interval[0]))
            .toList();

    // Merge overlaping intervals
    final List<int[]> merged = new ArrayList<>();
    for (final var interval : flatSchedule) {
      if (merged.isEmpty() || interval[0] > merged.getLast()[1]) {
        merged.add(interval);
      } else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
      }
    }

    final List<int[]> result = new ArrayList<>();
    for (int i = 1; i < merged.size(); i++) {
      if (merged.get(i - 1)[1] < merged.get(i)[0]) {
        result.add(new int[] {merged.get(i - 1)[1], merged.get(i)[0]});
      }
    }

    return result.toArray(new int[result.size()][]);
  }

  /** Interval record */
  private record Interval(int start, int end) {}
}
