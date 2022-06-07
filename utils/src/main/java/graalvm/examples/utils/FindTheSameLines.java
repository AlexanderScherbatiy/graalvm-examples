package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FindTheSameLines {

    private static int lineNumber = 0;

    public static void findTheSameLines(String fileName) throws IOException {
        findTheSameLines(Paths.get(fileName));
    }

    public static void findTheSameLines(Path path) throws IOException {
        Map<LineItem, LineItem> items = new HashMap<>();
        try (Stream<String> stream = Files
                .lines(path)
                .map(line -> {
                    lineNumber++;
                    return normalizeLine(line);
                })
                .filter(line -> includeLine(line))
        ) {
            stream.forEach(line -> {
                LineItem item = new LineItem(line, lineNumber);
                if (items.containsKey(item)) {
                    System.out.printf("%d:%d %s%n",
                            items.get(item).lineNumber, item.lineNumber, item.line);
                } else {
                    items.put(item, item);
                }
            });
        }
    }

    private static String normalizeLine(String line) {
        return line.trim();
    }

    private static boolean includeLine(String line) {
        return line.startsWith("JNIRuntimeAccess") || line.startsWith("RuntimeReflection");
    }

    private static class LineItem {
        private final String line;
        private final int lineNumber;

        public LineItem(String line, int lineNumber) {
            this.line = line;
            this.lineNumber = lineNumber;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof LineItem) {
                LineItem lineItem = (LineItem) other;
                return line.equals(lineItem.line);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return line.hashCode();
        }
    }
}
