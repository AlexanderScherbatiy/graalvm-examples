public class JNIExample {

    static {
        System.loadLibrary("JNIExample");
    }

    public static void hello() {
        nativeHello();
    }

    private static native void nativeHello();
}
