import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MathOperation extends Remote {
    double compute(Task task) throws RemoteException;
}
