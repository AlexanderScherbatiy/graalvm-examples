public class Animals {
    static {
        System.loadLibrary("Animals");
    }

    interface Animal {
        String sound();
    }

    public static class Cat implements Animal {
        public String sound() {
            return "meow!";
        }
    }

    public static class Dog implements Animal {
        public String sound() {
            return "bark!";
        }
    }

    public static class MyDog implements Animal {
        public String sound() {
            return "my bark!";
        }
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
