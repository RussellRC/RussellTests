package org.russell.dsa;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapPractice {

  public List<Integer> findKClosest(final int[] nums, final int k, final int target) {

    final Comparator<Element> sortByDistance = Comparator.comparingInt(e -> e.distance);
    final PriorityQueue<Element> heap = new PriorityQueue<>(nums.length, sortByDistance);

    for (int num : nums) {
      int distance = Math.abs(num - target);

      if (heap.size() < k) {
        heap.add(new Element(num, distance));
      } else if (distance < heap.peek().distance) {
        heap.remove();
        heap.add(new Element(num, distance));
      }
    }

    final var result = heap.stream().map(e -> e.number).sorted().toList();
    return result;
  }

  private static final class Element {
    final int number;
    final int distance;

    private Element(int number, int distance) {
      this.number = number;
      this.distance = distance;
    }
  }

  /**
   * https://www.hellointerview.com/learn/code/heap/merge-k-sorted-lists
   * https://leetcode.com/problems/merge-k-sorted-lists/
   */
  public static ListNode mergeKLists(final ListNode[] lists) {

    final Comparator<ListNode> comparator = Comparator.comparingInt(o -> o.val);
    final var heap = new PriorityQueue<>(lists.length, comparator);

    for (final var listNode : lists) {
      // add all the first nodes to the list
      heap.add(listNode);
    }

    final var firstNode = heap.poll();
    if (firstNode != null && firstNode.next != null) {
      heap.add(firstNode.next);
    }

    var current = firstNode;
    while (!heap.isEmpty()) {
      final var node = heap.poll();
      if (node.next != null) {
        heap.add(node.next);
      }

      current.next = node;
      current = node;
    }

    return firstNode;
  }

  /** ListNode */
  public static final class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
