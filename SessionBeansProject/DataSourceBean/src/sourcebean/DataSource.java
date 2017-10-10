package sourcebean;

import employee.Employee;

import javax.ejb.EJBObject;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface DataSource extends EJBObject {

    void setUpConnection() throws SQLException, NamingException, RemoteException;

    List<Employee> getAllEmployees() throws SQLException, RemoteException, NamingException;

    List<Employee> getEmployeesByName(String name) throws SQLException, RemoteException, NamingException;

    Employee getEmployeeById(int id) throws SQLException, RemoteException, NamingException;

}
