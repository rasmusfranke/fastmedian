package se.franke.fastmedian;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Stats {
    public static final int MAX_VALUE = 1000;

    public static int median(final IntStream values) {
        notNull(values);

        final long[] valueMap = createValueMap(values);

        long totalValues = Arrays.stream(valueMap).sum();
        if (!isOdd(totalValues)) {
            throw new IllegalArgumentException("even sized collections not supported");
        }

        int i = 0; // How many numbers we have parsed from the stream
        for (int value = 1; value < valueMap.length; value++) {
            long valueQty = valueMap[value];
            i += valueQty;
            if (i > totalValues / 2) {
                return value;
            }
        }
        throw new IllegalStateException("Unable to calculate median, most likely a programming error");
    }

    // Creates an array with the number of times each value appears in the stream, effectivly compressing the stream.
    // The index of of the array is the number represented.
    static long[] createValueMap(final IntStream values) {
        long[] valueMap = new long[MAX_VALUE + 1];
        values.forEach((i) -> {
            isTrue(i > 0 && i <= 1000, "Invalid values provided");
            valueMap[i]++;
        });
        return valueMap;
    }

    private static boolean isOdd(final long amountOfValues) {
        return amountOfValues % 2 == 1;
    }
}
