package graalvm.examples.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.printf("Provide arguments: GenerateJNIRuntimeAccess file.json%n");
            System.exit(0);
        }

        String command = args[0];

        switch (command) {
            case "GenerateJNIRuntimeAccess":
                checkArgument(args, 1, "JSON file is not provided" +
                        " as the second argument");
                generateRuntimeAccess(args[1]);
                break;
            case "UIClassIDConverter":
                checkArgument(args, 1, "File with component UIs is not" +
                        " provided as the second argument");
                UIClassIDConverter.convertUIClassID(args[1]);
                break;
            default:
                String msg = String.format("Command '%s' not found!", command);
                throw new Exception(msg);
        }
    }

    private static void generateRuntimeAccess(String file) throws Exception {

        List<GenerateJNIRuntimeAccess.JNIClass> jniClasses = GenerateJNIRuntimeAccess.parse(file);

        for (GenerateJNIRuntimeAccess.JNIClass jniClass : jniClasses) {
            dump("JNIRuntimeAccess.register(%s.class);", normalizeClassName(jniClass.getName()));
            if (jniClass.getFields() != null) {
                String fields = jniClass.getFields()
                        .stream()
                        .map(field -> String.format("\"%s\"", field.getName()))
                        .collect(Collectors.joining(", "));
                dump("JNIRuntimeAccess.register(fields(access, \"%s\", %s));", jniClass.getName(), fields);
            }

            if (jniClass.getMethods() != null) {
                for (GenerateJNIRuntimeAccess.JNIClassMethod jniMethod : jniClass.getMethods()) {
                    String parameterTypes = "";
                    if (jniMethod.getParameterTypes().size() > 0) {
                        parameterTypes = jniMethod.getParameterTypes()
                                .stream()
                                .map(type -> String.format("%s.class", normalizeClassName(type)))
                                .collect(Collectors.joining(", "));
                    }

                    if (!parameterTypes.isEmpty()) {
                        parameterTypes = ", " + parameterTypes;
                    }

                    if (jniMethod.getName().equals("<init>")) {
                        dump("JNIRuntimeAccess.register(constructor(access, \"%s\"%s));",
                                jniClass.getName(), parameterTypes);
                    } else {
                        dump("JNIRuntimeAccess.register(method(access, \"%s\", \"%s\"%s));",
                                jniClass.getName(), jniMethod.getName(), parameterTypes);
                    }
                }
            }
            dump("");
        }
    }

    private static String normalizeClassName(String className) {
        return className.replace('$', '.');
    }

    private static void checkArgument(String[] args, int index, String errorMessage) throws Exception {
        if (index >= args.length) {
            throw new Exception(errorMessage);
        }
    }

    private static void dump(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }
}
