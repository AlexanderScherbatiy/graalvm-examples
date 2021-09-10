package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FindCommonLines {

    public static void findCommonLines(String fileName1, String fileName2) throws IOException {
        findCommonLines(Paths.get(fileName1), Paths.get(fileName2));
    }

    public static void findCommonLines(Path path1, Path path2) throws IOException {

        Set<String> common = new HashSet<>();
        Set<String> filter = new HashSet<>();
        Set<String> filter1 = new HashSet<>();
        Set<String> filter2 = new HashSet<>();
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        try (Stream<String> stream = Files.lines(path1)) {
            stream.forEach(line -> {
                set1.add(line);
            });
        }

        try (Stream<String> stream = Files.lines(path2)) {
            stream.forEach(line -> {
                if (set1.contains(line)) {
                    common.add(line);
                    set1.remove(line);
                } else {
                    set2.add(line);
                }
            });
        }

        System.out.printf("// Common:%n");
        try (Stream<String> stream = Files.lines(path1)) {
            stream.forEach(line -> {
                dump(common, filter, line);
            });
        }

        try (Stream<String> stream = Files.lines(path2)) {
            stream.forEach(line -> {
                dump(common, filter, line);
            });
        }

        System.out.printf("// file %s:%n", path1.getFileName());
        try (Stream<String> stream = Files.lines(path1)) {
            stream.forEach(line -> {
                dump(set1, filter1, line);
            });
        }

        System.out.printf("// file %s:%n", path2.getFileName());
        try (Stream<String> stream = Files.lines(path2)) {
            stream.forEach(line -> {
                dump(set2, filter2, line);
            });
        }
    }

    private static void dump(Set<String> set, Set<String> filter, String line) {
        if (line.trim().isEmpty()) {
            System.out.printf("%n");
        } else if (set.contains(line) && !filter.contains(line)) {
            filter.add(line);
            System.out.printf("%s%n", line);
        }
    }
}
