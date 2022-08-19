package graalvm.examples.utils;

public class GenerateRuntimeReflection {

    public static void generateRuntimeReflection(String file) throws Exception {
        GenerateRegisteredClasses.generateRuntimeAccess(file, "RuntimeReflection");
    }
}
