package org.russell.practice.unsorted;

import java.util.ArrayList;
import java.util.List;

public class FlattenNestedLists {

  public List<Integer> flatten(final List<Object> list) {
    final List<Integer> result = new ArrayList<>();
    flatten(list, result);

    return result;
  }

  public void flatten(final List<?> list, final List<Integer> result) {
    for (final Object element : list) {
      if (element instanceof Integer) {
        result.add((Integer) element);
      } else if (element instanceof List) {
        flatten((List<?>) element, result);
      } else {
        throw new RuntimeException("WTF");
      }
    }
  }
}
