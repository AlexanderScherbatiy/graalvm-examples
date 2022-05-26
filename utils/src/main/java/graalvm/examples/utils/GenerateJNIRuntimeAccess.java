package graalvm.examples.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateJNIRuntimeAccess {

    public static List<JNIClass> parse(String file) throws IOException {
        return parse(new File(file));
    }

    public static List<JNIClass> parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<JNIClass>>() {
        });
    }

    public static void generateRuntimeAccess(String file) throws Exception {

        List<GenerateJNIRuntimeAccess.JNIClass> jniClasses = GenerateJNIRuntimeAccess.parse(file);

        for (GenerateJNIRuntimeAccess.JNIClass jniClass : jniClasses) {
            dump("JNIRuntimeAccess.register(%s);", convertClassName(jniClass.getName()));
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
                                .map(type -> convertClassName(type))
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
        String name = className;
        if (name.startsWith("[L")) {
            name = name.substring(2, name.length() - 1);
        }
        return name;
    }

    private static final Set<String> PRIMITIVE_TYPES = Set.of(
            "int", "long", "double", "float", "char", "byte", "boolean",
            "int[]", "long[]", "double[]",  "float[]", "char[]", "byte[]", "boolean[]");

    private static String convertClassName(String className) {
        String normalizedClassName = normalizeClassName(className);
        if (normalizedClassName.startsWith("java.") || PRIMITIVE_TYPES.contains(className)) {
            normalizedClassName = normalizedClassName.replace('$', '.');
            return String.format("%s.class", normalizedClassName);
        }

        return String.format("clazz(access, \"%s\")", normalizedClassName);
    }


    private static void dump(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }

    public static class JNIClass {

        private String name;
        private List<JNIClassField> fields;
        private List<JNIClassMethod> methods;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<JNIClassField> getFields() {
            return fields;
        }

        public void setFields(List<JNIClassField> fields) {
            this.fields = fields;
        }

        public List<JNIClassMethod> getMethods() {
            return methods;
        }

        public void setMethods(List<JNIClassMethod> methods) {
            this.methods = methods;
        }
    }

    public static class JNIClassField {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class JNIClassMethod {

        private String name;
        private List<String> parameterTypes;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getParameterTypes() {
            return parameterTypes;
        }

        public void setParameterTypes(List<String> parameterTypes) {
            this.parameterTypes = parameterTypes;
        }
    }
}
