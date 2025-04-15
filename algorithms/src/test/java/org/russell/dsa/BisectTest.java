package org.russell.dsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BisectTest {

    @Test
    void bisectRight() {
        final var arr = new int[]{1, 3, 4, 4, 4, 6, 7};
        assertEquals(5, Bisect.bisectRight(arr, 4, 0, arr.length));
        assertEquals(2, Bisect.bisectLeft(arr, 4, 0, arr.length));
    }
}
