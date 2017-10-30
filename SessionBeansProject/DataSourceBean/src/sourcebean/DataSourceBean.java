package sourcebean;

import employee.Employee;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSourceBean implements SessionBean {

    private Connection connection;

    public void setUpConnection() throws SQLException, NamingException {
        InitialContext initContext = new InitialContext();
        javax.sql.DataSource dataSource = (javax.sql.DataSource) initContext.lookup("jdbc/postgres");
        connection = dataSource.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }


    public DataSourceBean() {

    }


    public List<Employee> getAllEmployees() throws SQLException, NamingException {
        setUpConnection();
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
        connection.close();
        return allEmployees;
    }


    public List<Employee> getEmployeesByName(String name) throws SQLException, NamingException {
        setUpConnection();
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
        connection.close();
        return severalEmployees;

    }


    public Employee getEmployeeById(int id) throws SQLException, NamingException {
        setUpConnection();
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
        connection.close();
        return employee;

    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {

    }


    public void ejbRemove() throws EJBException {

    }

    public void ejbActivate() throws EJBException {

    }


    public void ejbPassivate() throws EJBException {

    }

    public void ejbCreate() {
    }
}
