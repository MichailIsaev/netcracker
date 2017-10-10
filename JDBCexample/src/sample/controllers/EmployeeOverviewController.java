package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Employee;
import sample.Main;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

/**
 * Created by michail on 23.09.17.
 */
public class EmployeeOverviewController {

    private Main main;
    private Alert alert;
    private final String SELECTQUERY = "SELECT DEPT.DNAME , EMP.ENAME , EMP.JOB , EMP.MGR , " +
            " EMP.SAL , EMP.COMM , DEPT.LOC , SALGRADE.GRADE FROM EMP , DEPT ,   SALGRADE" +
            " WHERE (EMP.SAL BETWEEN SALGRADE.LOSAL AND SALGRADE.HISAL) AND EMP.DEPTNO = DEPT.DEPTNO AND EMP.EMPNO ";


    @FXML
    MenuButton menuButton;
    @FXML
    MenuItem add;
    @FXML
    MenuItem delete;
    @FXML
    private Label employeeId;
    @FXML
    private TextField idInput;

    @FXML
    private Button idButton;

    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, String> empNameColumn;
    @FXML
    private TableColumn<Employee, String> empJobColumn;
    @FXML
    private TableColumn<Employee, String> deptNameColumn;
    @FXML
    private TableColumn<Employee, String> locateColumn;
    @FXML
    private TableColumn<Employee, Integer> gradeColumn;
    @FXML
    private TableColumn<Employee, Integer> empSalColumn;
    @FXML
    private TableColumn<Employee, Integer> empCommColumn;
    @FXML
    private TableColumn<Employee, Integer> empMgrColumn;

    public void setMainApp(Main main) {
        this.main = main;
    }

    public EmployeeOverviewController() {
    }

    @FXML
    private void query() throws IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Michail-PC:1521:XE", "IMA_6307", "12345");
            Statement stmt = connection.createStatement();
            if (!Pattern.compile("^[0-9]+$").matcher(idInput.getText()).matches()) idInput.setText("");
            if (idInput.getText().isEmpty()) throw new NullPointerException("Пустое поле ввода!");
            if (main.getEmployees().size() != 0) {
                main.getEmployees().remove(0);
            }
            int ID = Integer.parseInt(idInput.getText());
            ResultSet rs = stmt.executeQuery(SELECTQUERY + "=" + ID);

            String eName = "", dName = "", job = "", loc = "";
            int mgr = 0, sal = 0, comm = 0, grade = 0;
            while (rs.next()) {
                eName = rs.getString("ENAME");
                dName = rs.getString("DNAME");
                job = rs.getString("JOB");
                mgr = rs.getInt("MGR");
                sal = rs.getInt("SAL");
                comm = rs.getInt("COMM");
                loc = rs.getString("LOC");
                grade = rs.getInt("GRADE");
            }
            if (eName == "") throw new NullPointerException("В БД нет работника с таким id! \n  " +
                    "(или добавленный работник не зарегестрирован в реестре");
            main.getEmployees().add(new Employee(dName, eName, job, mgr, sal, comm, loc, grade));
            stmt.close();
            connection.close();
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getLocalizedMessage());
            alert.show();
            e.printStackTrace();
        } catch (NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        } finally {
            table.setItems(main.getEmployees());
        }
    }

    @FXML
    private void viewDeleteEmployeePane() throws IOException {
        main.setDeletePaneView();
    }

    @FXML
    private void viewAddEmployeePane() throws IOException {
        main.setAddPaneView();
    }

    @FXML
    private void initialize() {
        empNameColumn.setCellValueFactory(celldata -> celldata.getValue().empNameProperty());
        empJobColumn.setCellValueFactory(celldata -> celldata.getValue().empJobProperty());
        empMgrColumn.setCellValueFactory(celldata -> celldata.getValue().empMgrProperty().asObject());
        deptNameColumn.setCellValueFactory(celldata -> celldata.getValue().deptNameProperty());
        empSalColumn.setCellValueFactory(celldata -> celldata.getValue().empSalProperty().asObject());
        empCommColumn.setCellValueFactory(celldata -> celldata.getValue().empCommProperty().asObject());
        locateColumn.setCellValueFactory(celldata -> celldata.getValue().locateProperty());
        gradeColumn.setCellValueFactory(celldata -> celldata.getValue().gradeProperty().asObject());
    }

}
