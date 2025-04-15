package org.russell.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.SequencedSet;

public class Backtracking {

  /** https://www.hellointerview.com/learn/code/backtracking/word-search */
  public boolean wordSearch(final char[][] board, final String word) {

    final StringBuilder search = new StringBuilder();
    int currentIndex = 0;
    final SequencedSet<Cell> path = new LinkedHashSet<>();

    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[y].length; x++) {
        final Cell cell = new Cell(y, x);
        path.add(cell);
        if (searchWord(board, word, currentIndex, search, cell, path)) {
          return true;
        }
        path.remove(cell);
      }
    }

    return false;
  }

  private boolean searchWord(
      final char[][] board,
      final String word,
      final int currentIndex,
      final StringBuilder search,
      final Cell origin,
      final SequencedSet<Cell> path) {

    if (isOutOfBounds(board, origin)) {
      return false;
    }

    if (currentIndex == word.length()) {
      return true;
    }

    if (word.charAt(currentIndex) == board[origin.y][origin.x]) {
      search.append(word.charAt(currentIndex));
      final List<Cell> neighbors =
          List.of(
              new Cell(origin.y, origin.x - 1),
              new Cell(origin.y - 1, origin.x),
              new Cell(origin.y, origin.x + 1),
              new Cell(origin.y + 1, origin.x));
      for (final Cell neighbor : neighbors) {
        if (!path.contains(neighbor)) {
          path.add(neighbor);
          if (searchWord(board, word, currentIndex + 1, search, neighbor, path)) {
            return true;
          }
          path.remove(neighbor);
        }
      }
      search.deleteCharAt(search.length() - 1);
    }

    return false;
  }

  private static boolean isOutOfBounds(char[][] board, final Cell cell) {
    return cell.y < 0 || cell.y >= board.length || cell.x < 0 || cell.x >= board[cell.y].length;
  }

  private record Cell(int y, int x) {}

  /** https://www.hellointerview.com/learn/code/backtracking/solution-space-trees */
  public List<String> letterCombinations(final String digits) {
    final Map<Integer, List<Character>> mappings = new HashMap<>();
    mappings.put(2, List.of('a', 'b', 'c'));
    mappings.put(3, List.of('d', 'e', 'f'));
    mappings.put(4, List.of('g', 'h', 'i'));
    mappings.put(5, List.of('j', 'k', 'l'));
    mappings.put(6, List.of('m', 'n', 'o'));
    mappings.put(7, List.of('p', 'q', 'r', 's'));
    mappings.put(8, List.of('t', 'u', 'v'));
    mappings.put(9, List.of('w', 'x', 'y', 'z'));

    final List<String> result = new ArrayList<>();

    final StringBuilder combination = new StringBuilder();
    computeCombinations(digits, 0, combination, result, mappings);

    return result;
  }

  /** Helper function */
  private void computeCombinations(
      final String digits,
      final int i,
      final StringBuilder combination,
      final List<String> result,
      final Map<Integer, List<Character>> mappings) {

    if (i == digits.length()) {
      result.add(combination.toString());
      return;
    }
    final int digit = Character.digit(digits.charAt(i), 10);
    for (final Character character : mappings.get(digit)) {
      combination.append(character);
      computeCombinations(digits, i + 1, combination, result, mappings);
      combination.deleteCharAt(combination.length() - 1);
    }
  }
}
