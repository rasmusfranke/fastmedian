package se.franke.fastmedian;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

// Only for convenience and manual test utility
public class Generator {

    public static void main(String[] args) throws IOException {
        generateFile(Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]), args[3]);
    }

    private static void generateFile(final int size, final int rangeMinInc, final int rangeMaxInc, final String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);

        IntStream stream = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(rangeMinInc, rangeMaxInc + 1));
        stream.limit(size).forEach(i -> {
            try {
                fileWriter.write(i + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        fileWriter.close();
    }
}
