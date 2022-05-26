package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FindNewLines {

    public static void main(String[] args) throws Exception {
        String file1 = args[0];
        String file2 = args[1];

        findNewLines(file1, file2);
    }

    public static void findNewLines(String fileName1, String fileName2) throws IOException {
        findNewLines(Paths.get(fileName1), Paths.get(fileName2));
    }

    public static void findNewLines(Path path1, Path path2) throws IOException {

        Set<String> set1 = toSet(path1);
        Set<String> set2 = toSet(path2);
        System.out.printf("path1: %s%n", path1);
        System.out.printf("path2: %s%n", path2);

        for (String line : set2) {
            if (line.isEmpty()) {
                continue;
            }
            if (!set1.contains(line)) {
                System.out.printf("%s%n", line);
            }
        }
    }

    private static Set<String> toSet(Path path) throws IOException {
        Set<String> set = new HashSet<>();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> {
                set.add(line.trim());
            });
        }
        return set;
    }
}
