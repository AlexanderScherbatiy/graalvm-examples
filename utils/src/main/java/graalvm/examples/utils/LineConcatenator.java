package graalvm.examples.utils;

import java.util.LinkedHashSet;
import java.util.Set;

class LineConcatenator {

    private StringBuilder sb = new StringBuilder();
    private boolean intermidiate;
    private Set<String> lines = new LinkedHashSet<>();

    public void add(String line) {

        if (line.isBlank()) {
            return;
        }

        line = line.trim();

        boolean endsWithComa = line.endsWith(",");

        if (intermidiate) {
            sb.append(' ').append(line);
            if (!endsWithComa) {
                intermidiate = false;
                lines.add(sb.toString());
                sb.setLength(0);
            }
        } else {
            if (endsWithComa) {
                intermidiate = true;
                sb.append(line);
            } else {
                lines.add(line);
            }
        }

    }

    public Set<String> getLines() {
        return lines;
    }
}
