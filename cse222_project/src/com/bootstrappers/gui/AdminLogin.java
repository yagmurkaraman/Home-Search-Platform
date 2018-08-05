package com.bootstrappers.gui;

import com.bootstrappers.backend.Admin;
import com.bootstrappers.backend.Member;
import com.bootstrappers.backend.SystemUserAbstract;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.applet.Main;

public class AdminLogin extends Application {

    @FXML
    private Button adminlogin;
    @FXML
    private TextField adminEmail;
    @FXML
    private TextField adminPassword;
    @FXML
    private AdminScreen adminScreen;

    public static void main(String[] args) {
        launch(args);
    }


    public void initialize() throws Exception {
        adminScreen = new AdminScreen();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        root.getStylesheets().add(Main.class.getResource("/bootstrap2.css").toExternalForm());
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    @FXML
    void adminLoginSelected(ActionEvent event) throws Exception {
        MainScreen.admin = new Admin(null, null, adminPassword.getText(), null, adminEmail.getText(), null);
        if (MainScreen.admin.login(MainScreen.bs.getUserArrayList())) {
            for (SystemUserAbstract m : MainScreen.bs.getUserArrayList()) {
                if (m.getEmail().equals(adminEmail.getText())) {
                    MainScreen.admin.setName(m.getName());
                    MainScreen.admin.setSurname(m.getSurname());
                    MainScreen.admin.setEmail(m.getEmail());
                }
            }
            Stage stage = (Stage) adminlogin.getScene().getWindow();
            adminScreen.start(stage);
        } else {
            MainScreen.showDialog((Stage) adminlogin.getScene().getWindow(), "Giriş başarısız!");
        }
    }
}
