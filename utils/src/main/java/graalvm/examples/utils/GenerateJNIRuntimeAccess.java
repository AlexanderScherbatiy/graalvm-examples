package graalvm.examples.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateJNIRuntimeAccess {

    private static final String REGISTER_PREFIX =
            System.getProperty("generate.register.class", "JNIRuntimeAccess");

    public static List<AcessClass> parse(String file) throws IOException {
        return parse(new File(file));
    }

    public static List<AcessClass> parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<AcessClass>>() {
        });
    }

    public static void generateRuntimeAccess(String file) throws Exception {

        List<AcessClass> jniClasses = GenerateJNIRuntimeAccess.parse(file);
        String prefix = REGISTER_PREFIX;
        for (AcessClass jniClass : jniClasses) {
            dump("%s.register(%s);", prefix, convertClassName(jniClass.getName()));
            if (jniClass.getFields() != null) {
                String fields = jniClass.getFields()
                        .stream()
                        .map(field -> String.format("\"%s\"", field.getName()))
                        .collect(Collectors.joining(", "));
                dump("%s.register(fields(access, \"%s\", %s));", prefix, jniClass.getName(), fields);
            }

            if (jniClass.getMethods() != null) {
                for (AccessClassMethod jniMethod : jniClass.getMethods()) {
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
                        dump("%s.register(constructor(access, \"%s\"%s));",
                                prefix, jniClass.getName(), parameterTypes);
                    } else {
                        dump("%s.register(method(access, \"%s\", \"%s\"%s));",
                                prefix, jniClass.getName(), jniMethod.getName(), parameterTypes);
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

    public static class AcessClass {

        private String name;
        private List<AccessClassField> fields;
        private List<AccessClassMethod> methods;
        private List<AccessClassMethod> queriedMethods;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AccessClassField> getFields() {
            return fields;
        }

        public void setFields(List<AccessClassField> fields) {
            this.fields = fields;
        }

        public List<AccessClassMethod> getMethods() {
            return methods;
        }

        public void setMethods(List<AccessClassMethod> methods) {
            this.methods = methods;
        }

        public List<AccessClassMethod> getQueriedMethods() {
            return queriedMethods;
        }

        public void setQueriedMethods(List<AccessClassMethod> queriedMethods) {
            this.queriedMethods = queriedMethods;
        }
    }

    public static class AccessClassField {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class AccessClassMethod {

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
