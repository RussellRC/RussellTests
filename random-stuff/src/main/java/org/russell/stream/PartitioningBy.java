package org.russell.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningBy {

    public static void main(String[] args) {
        final List<Integer> intList = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        final Map<Boolean, List<Integer>> groups = intList.stream()
                .collect(Collectors.partitioningBy(n -> n > 2));
        System.out.println(groups);
    }
}
