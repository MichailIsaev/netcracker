import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    public void setA(double a) {
        this.a = a;
    }

    private double a;

    public void setB(double b) {
        this.b = b;
    }

    private double b;

    public void setOperation(char operation) {
        this.operation = operation;
    }

    private char operation;

    public Task(double a, double b, char operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    Task() {
    }
}
