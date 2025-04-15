package org.russell.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.russell.dsa.HeapPractice.ListNode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeapTest {

  private HeapPractice hp;

  @BeforeEach
  void beforeEach() {
    hp = new HeapPractice();
  }

  @Test
  void findKClosest() {
    assertEquals(List.of(-1, -0, 1), hp.findKClosest(new int[]{-1, 0, 1, 4, 6}, 3, 1));
  }

  @Test
  void mergeKLists1() {
    // [[3,4,6],[2,3,5],[-1,6]]
    final var l1 = new ListNode(3, new ListNode(4, new ListNode(6)));
    final var l2 = new ListNode(2, new ListNode(3, new ListNode(5)));
    final var l3 = new ListNode(-1, new ListNode(6));

    final var lists = new ListNode[] {l1, l2, l3};
    final var result = HeapPractice.mergeKLists(lists);
    assertEquals(-1, result.val);
    assertEquals(2, result.next.val);
    assertEquals(3, result.next.next.val);
    assertEquals(3, result.next.next.next.val);
    assertEquals(4, result.next.next.next.next.val);
    assertEquals(5, result.next.next.next.next.next.val);
    assertEquals(6, result.next.next.next.next.next.next.val);
    assertEquals(6, result.next.next.next.next.next.next.next.val);
  }
}
