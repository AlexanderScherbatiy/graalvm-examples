package graalvm.examples.utils;

public class GenerateJNIRuntimeAccess {

    public static void generateRuntimeAccess(String file) throws Exception {
        GenerateRegisteredClasses.generateRuntimeAccess(file, "JNIRuntimeAccess");
    }
}
