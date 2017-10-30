package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.AddOverviewController;
import sample.controllers.DeleteOverviewController;
import sample.controllers.EmployeeOverviewController;

import java.io.IOException;

public class Main extends Application {

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    public Stage primaryStage;
    private EmployeeOverviewController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        setRootPaneView();
    }


    public Main() {
    }

    public void setRootPaneView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/rootPane.fxml"));
        Parent root = loader.load();
        EmployeeOverviewController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void setDeletePaneView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/deletePane.fxml"));
        Parent root = loader.load();
        DeleteOverviewController controller = loader.getController();
        controller.setMainApp(this);
        Stage stage = this.primaryStage;
        stage.setScene(new Scene(root));
        stage.setTitle("Delete");
        stage.show();
    }

    public void setAddPaneView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/addPane.fxml"));
        Parent root = loader.load();
        AddOverviewController controller = loader.getController();
        controller.setMainApp(this);
        Stage stage = this.primaryStage;
        stage.setScene(new Scene(root));
        stage.setTitle("Add");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }


}
