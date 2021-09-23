import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {

    public static void main(String[] args) throws Exception {

        SampleObject sample1 = new SampleObject(11, "Hello, World!");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(sample1);
        byte[] bytes = out.toByteArray();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        SampleObject sample2 = (SampleObject) is.readObject();

        System.out.printf("sample1: %s%n", sample1);
        System.out.printf("sample2: %s%n", sample2);

        if (!sample1.equals(sample2)) {
            throw new RuntimeException("Objects are not equal!");
        }
    }
}

