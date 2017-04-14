package se.franke.fastmedian;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class StatsTest {
    @Test(expected = NullPointerException.class)
    public void nullStreamFails() {
        Stats.median(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyStreamFails() {
        Stats.median(IntStream.empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void evenStreamFails() {
        Stats.median(IntStream.rangeClosed(1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooSmallFails() {
        Stats.median(IntStream.rangeClosed(0, 1));
    }

    @Test
    public void negativeNumbersSuccess() {
        int result = Stats.median(IntStream.of(1, -1, 0));
        assertEquals(0, result);
    }

    @Test
    public void oddStreamSuccess() {
        IntStream input = IntStream.of(4, 1, 6, 3, 7, 8, 7);
        int result = Stats.median(input);
        assertEquals(6, result);
    }
}
