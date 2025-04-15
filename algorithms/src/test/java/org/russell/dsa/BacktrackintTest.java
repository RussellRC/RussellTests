package org.russell.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BacktrackintTest {

  private Backtracking bt;

  @BeforeEach
  void beforeEach() {
    bt = new Backtracking();
  }

  @Test
  void wordSearch() {
    char[][] board;

    board =
        new char[][] {
          {'B', 'L', 'C', 'H'},
          {'D', 'E', 'L', 'T'},
          {'D', 'A', 'K', 'A'},
        };
    assertTrue(bt.wordSearch(board, "BLEAK"));
    assertTrue(bt.wordSearch(board, "TAK"));
    assertFalse(bt.wordSearch(board, "BLO"));
  }

  @Test
  void letterCombinations() {
    final var expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
    assertEquals(expected, bt.letterCombinations("23"));
  }
}
