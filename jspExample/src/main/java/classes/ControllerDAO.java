package classes;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ControllerDAO<T> {

    List<T> getAllEmployees() throws SQLException;

    List<T> getEmployeesByName(String name) throws SQLException;

    T getEmployeeById(int id) throws SQLException;

}
