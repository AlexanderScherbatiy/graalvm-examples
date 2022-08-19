public class JAWTExample {

    static {
        System.loadLibrary("JAWTExample");
    }

    public static void main(String[] args) {
        System.out.printf("Native JAWT sample!%n");
        long jawt = getJAWT();
        System.out.printf("jawt: %d%n", jawt);

    }

    private static native long getJAWT();
}
