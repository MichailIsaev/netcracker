package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

/**
 * Created by michail on 24.09.17.
 */
public class DeleteOverviewController {

    private Main main;

    private Alert alert;

    @FXML
    private TextField idOnDelete;

    private final String DELETEQUERY = "DELETE FROM EMP WHERE EMP.EMPNO = ";

    public void setMainApp(Main main) {
        this.main = main;
    }

    public DeleteOverviewController() {
    }


    @FXML
    private void deleteEmployee() throws IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Michail-PC:1521:XE", "IMA_6307", "12345");
            Statement stmt = connection.createStatement();
            if (Double.isNaN(Double.valueOf(idOnDelete.getText()))) idOnDelete.setText("");
            if (idOnDelete.getText().isEmpty()) throw new NullPointerException("Пустая строка ввода id сотрудника");
            int ID = Integer.parseInt(idOnDelete.getText());
            stmt.executeQuery(DELETEQUERY + ID);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Сотрудник с id = " + ID + " удален!");
            info.show();

        } catch (SQLException | NullPointerException | ClassNotFoundException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            e.printStackTrace();
        }
        finally {
            main.setRootPaneView();
        }


    }
}
