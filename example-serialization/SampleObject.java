import java.io.Serializable;
import java.util.Objects;

public class SampleObject implements Serializable {

    private int code;
    private String message;

    public SampleObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof SampleObject) {
            SampleObject that = (SampleObject) obj;
            return this.code == that.code
                    && Objects.equals(this.message, that.message);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        return String.format("SampleObject(code: %d, message: %s)", code, message);
    }
}

