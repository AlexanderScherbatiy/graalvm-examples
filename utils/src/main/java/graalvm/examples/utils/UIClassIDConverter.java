package graalvm.examples.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UIClassIDConverter {

    private static final String COMP_UI_PACKAGE = System.getProperty("CompUIPackage", "");

    public static void convertUIClassID(String fileName) throws IOException {
        convertUIClassID(Paths.get(fileName));
    }

    public static void convertUIClassID(Path path) throws IOException {

        try (Stream<String> stream = Files.lines(path)) {

            stream.forEach(line -> {
                if (line.trim().isEmpty()) {
                    return;
                }
                parseLine(line);
            });
        }
    }

    private static void parseLine(String line) {
        int index1 = line.indexOf(',');
        int index2 = line.indexOf('+');

        String uiID = line.substring(0, index1).trim();
        String compUI = line.substring(index2 + 1).trim();
        compUI = compUI.substring(1, compUI.length() - 2);
        printLine(uiID, compUI);
    }

    private static void printLine(String uiID, String compUI) {
        System.out.printf("    case \"%s.%s\" : return %s.%s.createUI(comp);%n", COMP_UI_PACKAGE, compUI, COMP_UI_PACKAGE, compUI);
    }
}
