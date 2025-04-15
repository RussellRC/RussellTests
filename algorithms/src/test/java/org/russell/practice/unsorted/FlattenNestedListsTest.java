package org.russell.practice.unsorted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlattenNestedListsTest {

    private FlattenNestedLists fnl;

    @BeforeEach
    void beforeEach() {
        fnl = new FlattenNestedLists();
    }

    @Test
    void test1() {
        final var nested = List.of(1, List.of(2, 3), List.of(4, List.of(5, 6)), 7);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), fnl.flatten(nested));
    }
}
