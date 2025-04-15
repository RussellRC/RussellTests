package org.russell.practice.hr;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/minesweeper/<br>
 * 'M' - unrevealed mine,<br>
 * 'E' - unrevealed empty square,<br>
 * 'B' - revealed blank square that has no adjacent mines (8 adj cells),<br>
 * digit ('1' to '8') - how many mines are adjacent to this revealed square, and<br>
 * 'X' - represents a revealed mine.<br>
 */
public class Minesweeper {

  public char[][] updateBoard(char[][] board, int[] click) {
    final int clickY = click[0];
    final int clickX = click[1];

    if (board[clickY][clickX] == 'M') {
      board[clickY][clickX] = 'X';
      return board;
    }

    final SequencedSet<Cell> pending = new LinkedHashSet<>();
    pending.add(new Cell(clickY, clickX));
    final Set<Cell> visited = new HashSet<>();

    while (!pending.isEmpty()) {
      final Cell current = pending.removeFirst();
      if (!visited.contains(current)) {
        visited.add(current);
        if (board[current.y][current.x] == 'E') {
          int adjMines = adjacentMines(board, current.y, current.x);
          if (adjMines != 0) {
            board[current.y][current.x] = Character.forDigit(adjMines, 10);
          } else {
            board[current.y][current.x] = 'B';
            addAdjacent(visited, pending, board, current.y, current.x);
          }
        }
      }
    }

    return board;
  }

  private static void addAdjacent(
      final Set<Cell> visited,
      final SequencedSet<Cell> pending,
      final char[][] board,
      final int y,
      final int x) {

    for (int adjY = y - 1; adjY <= y + 1; adjY++) {
      for (int adjX = x - 1; adjX <= x + 1; adjX++) {
        final Cell cell = new Cell(adjY, adjX);
        if (!visited.contains(cell)
            && !isOutOfBounds(board, adjY, adjX)
            && !(adjY == y && adjX == x)) {
          pending.add(cell);
        }
      }
    }
  }

  private static int adjacentMines(char[][] board, int y, int x) {
    int adjMines = 0;
    for (int adjY = y - 1; adjY <= y + 1; adjY++) {
      for (int adjX = x - 1; adjX <= x + 1; adjX++) {
        if (!(adjY == y && adjX == x)) {
          if (isMine(board, adjY, adjX)) {
            adjMines++;
          }
        }
      }
    }

    return adjMines;
  }

  private static boolean isMine(char[][] board, int y, int x) {
    return !isOutOfBounds(board, y, x) && board[y][x] == 'M';
  }

  private static boolean isOutOfBounds(char[][] board, int y, int x) {
    return y < 0 || y >= board.length || x < 0 || x >= board[y].length;
  }

  public record Cell(int y, int x) {}
}
