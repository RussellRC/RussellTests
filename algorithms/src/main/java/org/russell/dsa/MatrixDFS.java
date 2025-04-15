package org.russell.dsa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixDFS {

  /** https://www.hellointerview.com/learn/code/depth-first-search/flood-fill */
  public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    final int originalColor = image[sr][sc];
    if (originalColor == newColor) {
      return image;
    }

    final Set<List<Integer>> visited = new HashSet<>();

    floodFill(image, sr, sc, newColor, originalColor, visited);
    return image;
  }

  /** floodFill helper */
  private static void floodFill(
      int[][] image, int y, int x, int newColor, int originalColor, Set<List<Integer>> visited) {
    if (visited.contains(List.of(y, x))) {
      return;
    }

    if (isOutOfBounds(image, y, x)) {
      return;
    }

    visited.add(List.of(y, x));
    if (image[y][x] == originalColor) {
      image[y][x] = newColor;
      floodFill(image, y - 1, x, newColor, originalColor, visited);
      floodFill(image, y + 1, x, newColor, originalColor, visited);
      floodFill(image, y, x - 1, newColor, originalColor, visited);
      floodFill(image, y, x + 1, newColor, originalColor, visited);
    }
  }

  /** https://www.hellointerview.com/learn/code/depth-first-search/number-of-islands */
  public static int numberOfIslands(int[][] matrix) {
    // We could do this with no additional space if we mutate the original matrix,
    // by turning 1s to 0s at visit time
    final Set<Node> visited = new HashSet<>();

    int islands = 0;
    for (int y = 0; y < matrix.length; y++) {
      for (int x = 0; x < matrix[y].length; x++) {
        if (matrix[y][x] == 1 && !visited.contains(new Node(y, x))) {
          islands++;
          numberOfIslands(matrix, y, x, visited);
        }
      }
    }
    return islands;
  }

  /** numberOfIslands helper */
  private static void numberOfIslands(int[][] matrix, int y, int x, Set<Node> visited) {
    if (isOutOfBounds(matrix, y, x)) {
      return;
    }

    final var node = new Node(y, x);
    if (visited.contains(node)) {
      return;
    }

    visited.add(node);
    if (matrix[y][x] == 1) {
      numberOfIslands(matrix, y - 1, x, visited);
      numberOfIslands(matrix, y + 1, x, visited);
      numberOfIslands(matrix, y, x - 1, visited);
      numberOfIslands(matrix, y, x + 1, visited);
    }
  }

  private static boolean isOutOfBounds(int[][] matrix, int y, int x) {
    return y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length;
  }

  private record Node(int y, int x){}
}
