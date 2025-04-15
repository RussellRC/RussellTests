package org.russell.practice.hr;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinesweeperTest {

  private Minesweeper ms;

  @BeforeEach
  void beforeEach() {
    ms = new Minesweeper();
  }

  @Test
  void test1() {
    final char[][] board =
        new char[][] {
          {'E', 'E', 'E', 'E', 'E'},
          {'E', 'E', 'M', 'E', 'E'},
          {'E', 'E', 'E', 'E', 'E'},
          {'E', 'E', 'E', 'E', 'E'},
        };
    final char[][] expected =
        new char[][] {
          {'B', '1', 'E', '1', 'B'},
          {'B', '1', 'M', '1', 'B'},
          {'B', '1', '1', '1', 'B'},
          {'B', 'B', 'B', 'B', 'B'},
        };

    assertArrayEquals(expected, ms.updateBoard(board, new int[] {3, 0}));
  }
}
