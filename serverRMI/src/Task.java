import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private double a, b;
    private char operation;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public char getOperation() {
        return operation;
    }
}
