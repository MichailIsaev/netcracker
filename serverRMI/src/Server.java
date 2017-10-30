

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server implements MathOperation {
    @Override
    public double compute(Task task) throws RemoteException {
        char c = task.getOperation();
        double result = 0;
        switch (c) {
            case '*':
                result = task.getA() * task.getB();
                break;
            case '+':
                result = task.getA() + task.getB();
                break;
            case '-':
                result = task.getA() - task.getB();
                break;
            case '/':
                result = task.getA() / task.getB();
                break;
            case '%':
                result = task.getA() % task.getB();
                break;
            case '^':
                result = Math.pow(task.getA(), task.getB());
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            Server serverObject = new Server();
            MathOperation stub = (MathOperation) UnicastRemoteObject.exportObject(serverObject, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("math", stub);
            System.out.println("Server ready.");

        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println("Server exception :" + e.getStackTrace());
        }
    }
}
