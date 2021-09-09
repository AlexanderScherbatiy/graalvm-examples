package graalvm.examples.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateJNIRuntimeAccess {

    public static List<JNIClass> parse(String file) throws IOException {
        return parse(new File(file));
    }

    public static List<JNIClass> parse(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<JNIClass>>() {
        });
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
