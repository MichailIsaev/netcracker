import exceptions.CharacterException;
import exceptions.DivisionByZeroException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.regex.Pattern;


public class Client {

    public static void main(String[] args) {
        String a = "", b = "", o = "";
        String pattern = "^[-+*^/%]$";
        Task task = new Task();
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите два числовых операнда: ");
            System.out.println("a: ");
            a = reader.readLine();
            task.setA(Double.valueOf(a));
            System.out.println("b: ");
            b = reader.readLine();
            task.setB(Double.valueOf(b));
            System.out.println("Введите оператор бинарной операции (в формате '*' , '+' , '-' , '%' , '^' , '/' ) :");
            o = reader.readLine();
            task.setOperation(o.charAt(0));
            if (!Pattern.matches(pattern, o)) {
                throw new CharacterException("Введенный символ не является бинарным оператором.");
            }
            if (o.charAt(0) == '/' && Double.valueOf(b) == 0) {
                throw new DivisionByZeroException("Деление на ноль!");
            }
            Registry registry = LocateRegistry.getRegistry(0);
            MathOperation stub = (MathOperation) registry.lookup("math");
            double response = stub.compute(task);
            System.out.println("Response : " + response);
        } catch (IOException | NotBoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException | DivisionByZeroException | CharacterException e) {
            System.out.println(e.getMessage());
        }
    }
}
