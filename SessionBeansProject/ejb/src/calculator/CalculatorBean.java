package calculator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class CalculatorBean implements SessionBean {

    private double a, b, currentResult, memory;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(double currentState) {
        this.currentResult = currentState;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public CalculatorBean() {
    }

    public void mul() {
        currentResult = a * b;
    }


    public void add() {
        currentResult = a + b;
    }


    public void sub() {
        currentResult = a - b;
    }


    public void div() {
        currentResult = a / b;
    }


    public void saveCurrentResultToMemory() {
        memory = currentResult;
    }

    public void fromMemoryToOperands() {
        a = memory;
        b = memory;
    }


    public void resetInMemory() {
        memory = 0;
    }


    public void setSessionContext(SessionContext sessionContext) throws EJBException {

    }

    public void ejbRemove() throws EJBException {

    }


    public void ejbActivate() throws EJBException {

    }

    public void ejbPassivate() throws EJBException {

    }


    public void ejbCreate() throws CreateException {

    }

}
