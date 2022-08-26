
public class JNIReflectionExample {

    public static void main(String[] args) throws Exception {
        callReflectionJNIExample();
    }

    public static void callJNIExample() {
        new JNIExample().hello();
    }

    public static void callReflectionJNIExample()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> cls = Class.forName("JNIExample");
        JNIExample jniExample = (JNIExample) cls.newInstance();
        jniExample.hello();
    }
}