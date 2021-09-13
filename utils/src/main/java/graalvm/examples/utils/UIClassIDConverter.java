package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class UIClassIDConverter {

    private static final String COMP_UI_PACKAGE = System.getProperty("CompUIPackage", "");
    private Set<String> filter = new HashSet<>();

    public void convertUIClassID(String fileName) throws IOException {
        convertUIClassID(Paths.get(fileName));
    }

    public void convertUIClassID(Path path) throws IOException {

        try (Stream<String> stream = Files.lines(path)) {

            stream.forEach(line -> {
                if (line.trim().isEmpty()) {
                    return;
                }
                parseLine(line);
            });
        }
    }

    // NimbusDefaults.java
    private void parseLine(String line) {
        line = line.trim();
        if (!line.startsWith("d.put")) {
            return;
        }
        int index1 = line.indexOf("LazyPainter");
        if (index1 < 0) {
            throw new RuntimeException("Index not found for line: " + line);
        }
        index1 = line.indexOf("\"", index1);
        if (index1 < 0) {
            throw new RuntimeException("Index not found for line: " + line);
        }
        index1++;
        int index2 = line.indexOf("\"", index1 + 1);
        String cls = line.substring(index1, index2);

        if (!filter.contains(cls)) {
            filter.add(cls);
            printLine(cls);
        }
    }

    // MetalLookAndFeel
    private static void parseLine2(String line) {
        int index1 = line.indexOf(',');
        int index2 = line.indexOf('+');

        String uiID = line.substring(0, index1).trim();
        String compUI = line.substring(index2 + 1).trim();
        compUI = compUI.substring(1, compUI.length() - 2);
        printLine2(uiID, compUI);
    }

    private static void printLine(String cls) {
        System.out.printf("    case \"%1$s\" : return new %1$s(ctx, which);%n", cls);
    }

    private static void printLine2(String uiID, String compUI) {
        System.out.printf("    case \"%s.%s\" : return %s.%s.createUI(comp);%n", COMP_UI_PACKAGE, compUI, COMP_UI_PACKAGE, compUI);
    }
}
