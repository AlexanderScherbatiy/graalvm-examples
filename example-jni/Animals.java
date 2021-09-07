public class Animals {
    static {
        System.loadLibrary("Animals");
    }

    public static void main(String[] args) {
        System.out.printf("Animals sample!%n");

        Animal cat = new Cat();
        Animal myDog = new MyDog();
        Animal nativeAnimal = createAnimal();

        System.out.printf("cat sound: %s%n", cat.sound());
        System.out.printf("my dog sound: %s%n", myDog.sound());
        System.out.printf("native sound: %s%n", nativeAnimal.sound());
    }

    private static native Animal createAnimal();
}
