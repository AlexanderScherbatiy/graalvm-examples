package graalvm.examples.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final String[] COMMANDS = {
            "GenerateJNIRuntimeAccess", "UIClassIDConverter", "FindCommonLines"};

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.printf("Provide arguments. Commands: %s%n.", String.join(", ", COMMANDS));
            System.exit(0);
        }

        String command = args[0];

        switch (command) {
            case "GenerateJNIRuntimeAccess":
                checkArgument(args, 1, "JSON file is not provided" +
                        " as the second argument");
                GenerateJNIRuntimeAccess.generateRuntimeAccess(args[1]);
                break;
            case "UIClassIDConverter":
                checkArgument(args, 1, "File with component UIs is not" +
                        " provided as the second argument");
                new UIClassIDConverter().convertUIClassID(args[1]);
                break;
            case "FindCommonLines":
                checkArgument(args, 2, "Two files are not provided" +
                        " as the second argument");
                FindCommonLines.findCommonLines(args[1], args[2]);
                break;
            default:
                String msg = String.format("Command '%s' not found!", command);
                throw new Exception(msg);
        }
    }

    private static void checkArgument(String[] args, int index, String errorMessage) throws Exception {
        if (index >= args.length) {
            throw new Exception(errorMessage);
        }
    }
}
