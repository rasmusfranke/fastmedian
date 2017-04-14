package se.franke.fastmedian;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.lang3.Validate.isTrue;

public class Parser {

    public static void main(String[] args) throws IOException {
        isTrue(args.length == 1,
                "A file must be specified with an odd number of line separated separated integers 0 < i <= 1000");
        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            IntStream numbersStream = lines.mapToInt(Integer::valueOf);

            long startTime = new Date().getTime();
            int median = Stats.median(numbersStream);
            System.out.println(String.format("Duration %d ms", new Date().getTime() - startTime));
            System.out.println(String.format("Median: %d", median));
        }
    }
}
