package calculator;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;


public interface Calculator extends EJBObject {
    void mul() throws RemoteException;

    void add() throws RemoteException;

    void sub() throws RemoteException;

    void div() throws RemoteException;

    void saveCurrentResultToMemory() throws RemoteException;

    void fromMemoryToOperands() throws RemoteException;

    void resetInMemory() throws RemoteException;

    void setA(double a) throws RemoteException;

    double getA() throws RemoteException;

    void setB(double a) throws RemoteException;

    double getB() throws RemoteException;

    double getCurrentResult() throws RemoteException;

    void setCurrentResult(double currentState) throws RemoteException;

    double getMemory() throws RemoteException;

    void setMemory(double memory) throws RemoteException;

}
