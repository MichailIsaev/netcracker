package classes;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MyControllerDAO implements ControllerDAO<Employee> {

    private Connection connection;

    @Resource(name = "jdbc/EmployeeDB")
    private DataSource dataSource;

    public void initialDataSource() {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/EmployeeDB");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        String query = "SELECT EMP.EMPNO , EMP.ENAME , EMP.JOB , EMP.HIREDATE , DEPT.DNAME " +
                "FROM EMP , DEPT WHERE DEPT.DEPTNO = EMP.DEPTNO";
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        List<Employee> allEmployees = new ArrayList<>();
        Employee employee;
        while (resultSet.next()) {
            employee = new Employee();
            employee.setEmployeeNumber(resultSet.getInt("EMPNO"));
            employee.setEmployeeName(resultSet.getString("ENAME"));
            employee.setPosition(resultSet.getString("JOB"));
            employee.setDepartmentName(resultSet.getString("DNAME"));
            employee.setHireDate(resultSet.getDate("HIREDATE"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public List<Employee> getEmployeesByName(String name) throws SQLException {
        String query = "SELECT EMP.EMPNO , EMP.ENAME , EMP.JOB , EMP.HIREDATE , DEPT.DNAME " +
                "FROM EMP , DEPT WHERE DEPT.DEPTNO = EMP.DEPTNO AND EMP.ENAME = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        List<Employee> severalEmployees = new ArrayList<>();
        Employee employee;
        while (resultSet.next()) {
            employee = new Employee();
            employee.setEmployeeNumber(resultSet.getInt("EMPNO"));
            employee.setEmployeeName(resultSet.getString("ENAME"));
            employee.setPosition(resultSet.getString("JOB"));
            employee.setDepartmentName(resultSet.getString("DNAME"));
            employee.setHireDate(resultSet.getDate("HIREDATE"));
            severalEmployees.add(employee);
        }
        return severalEmployees;

    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String query = "SELECT EMP.EMPNO , EMP.ENAME , EMP.JOB , EMP.HIREDATE , DEPT.DNAME " +
                "FROM EMP , DEPT WHERE DEPT.DEPTNO = EMP.DEPTNO AND EMP.EMPNO=" + id + "";

        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet resultSet = statement.getResultSet();
        Employee employee = null;
        while (resultSet.next()) {
            employee = new Employee();
            employee.setEmployeeNumber(resultSet.getInt("EMPNO"));
            employee.setEmployeeName(resultSet.getString("ENAME"));
            employee.setPosition(resultSet.getString("JOB"));
            employee.setDepartmentName(resultSet.getString("DNAME"));
            employee.setHireDate(resultSet.getDate("HIREDATE"));
        }
        return employee;

    }

    public Connection getConnection() {
        return connection;
    }

}
