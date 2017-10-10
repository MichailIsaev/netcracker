package calculator;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface CalculatorHome extends EJBHome {
    Calculator create() throws CreateException, RemoteException;
}
