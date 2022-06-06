package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ConcatenateJNI {

    private static final String REGISTER_PREFIX =
        System.getProperty("generate.register.class", "JNIRuntimeAccess");

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.out.printf("Proved path to two files.%n");
        }
        String file1 = args[0];
        String file2 = args[1];
        concatenateJNI(file1, file2);
    }

    public static void concatenateJNI(String fileName1, String fileName2) throws IOException {
        concatenateJNI(Paths.get(fileName1), Paths.get(fileName2));
    }

    public static void concatenateJNI(Path path1, Path path2) throws IOException {

        Set<String> set1 = toSet(path1);
        Set<String> set2 = toSet(path2);
        System.out.printf("path1: %s%n", path1);
        System.out.printf("path2: %s%n", path2);
        System.out.printf("%n%n");

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

        LineParser parser = new LineParser();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(line -> {
                parser.addLine(line);
            });
        }
        return parser.getSet();
    }

    private static class LineParser {

        private final Set<String> set = new HashSet<>();
        private boolean parsed = false;
        private StringBuilder builder = new StringBuilder();

        public void addLine(String line) {

            line = line.trim();

            if (!parsed) {
                if (line.startsWith(REGISTER_PREFIX + ".register")) {
                    if (line.endsWith(",")) {
                        parsed = true;
                        builder.setLength(0);
                        builder.append(line);
                    } else {
                        set.add(line);
                    }
                }
            } else {
                builder.append(" ").append(line);
                if (!line.endsWith(",")) {
                    set.add(builder.toString());
                    parsed = false;
                }
            }
        }

        public Set<String> getSet() {
            return set;
        }
    }
}
