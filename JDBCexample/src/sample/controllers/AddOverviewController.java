package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Main;


import java.io.IOException;
import java.sql.*;

/**
 * Created by michail on 24.09.17.
 */
public class AddOverviewController {

    private Main main;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField jobField;
    @FXML
    private TextField mgrField;
    @FXML
    private TextField hireDateField;
    @FXML
    private TextField salField;
    @FXML
    private TextField commField;
    @FXML
    private TextField deptNo;
    @FXML
    Button addEmployee;


    public void setMainApp(Main main) {
        this.main = main;
    }

    public AddOverviewController() {
    }

    @FXML
    private void addEmployee() throws IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Michail-PC:1521:XE", "IMA_6307", "12345");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO EMP  VALUES( ? , ? , ? , ? ," + "TO_DATE(?)" + ",?,?,?)");
            stmt.setInt(1, Integer.valueOf(idField.getText()));
            stmt.setString(2, nameField.getText());
            stmt.setString(3, jobField.getText());
            stmt.setInt(4, Integer.valueOf(mgrField.getText()));
            System.out.println((hireDateField.getText()));
            stmt.setString(5, (hireDateField.getText()));
            stmt.setInt(6, Integer.valueOf(salField.getText()));
            stmt.setInt(7, Integer.valueOf(commField.getText()));
            stmt.setInt(8, Integer.valueOf(deptNo.getText()));
            stmt.execute();
            stmt.close();
            connection.close();
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Сотрудник добавлен!");
            info.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ошибка вводимых данных! \nИщите сами ;)");
            alert.show();
        }
        finally {
            main.setRootPaneView();
        }

    }


}
