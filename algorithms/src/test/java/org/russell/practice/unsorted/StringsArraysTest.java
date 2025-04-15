package org.russell.practice.unsorted;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringsArraysTest {

    private StringsArrays sa;

    @BeforeEach
    void beforeEach() {
        sa = new StringsArrays();
    }

    @Test
    void isSubsequence() {
        assertTrue(sa.isSubsequence("abcdef", "bde"));
        assertFalse(sa.isSubsequence("abcdef", "dc"));
    }
}
