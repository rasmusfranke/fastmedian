package se.franke.fastmedian;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.Validate.notNull;

public class Stats {
    /**
     * Performs a variant of a counting sort on an IntStream to calculate median. Performance decreases with higher
     * values rather longer streams
     *
     * @param values size must be odd
     * @return
     */
    public static int median(final IntStream values) {
        notNull(values);

        // key is the numeric value, map value is the number of occurences of each value
        final Map<Integer, Long> valueMap = groupByValue(values);

        long streamLength = valueMap.values().stream().mapToLong(Long::longValue).sum();
        if (!sizeIsOdd(streamLength)) {
            throw new IllegalArgumentException("even sized collections not supported");
        }

        int i = 0; // How many numbers we have parsed from the stream
        for (Map.Entry<Integer, Long> e : valueMap.entrySet()) {
            i += e.getValue();
            if (i > streamLength / 2) {
                return e.getKey();
            }
        }
        throw new IllegalStateException("Unable to calculate median, most likely a programming error");
    }

    private static Map<Integer, Long> groupByValue(final IntStream values) {
        return values.boxed().parallel().collect(groupingBy(identity(), TreeMap::new, Collectors.counting()));
    }

    private static boolean sizeIsOdd(final long amountOfValues) {
        return amountOfValues % 2 == 1;
    }
}
