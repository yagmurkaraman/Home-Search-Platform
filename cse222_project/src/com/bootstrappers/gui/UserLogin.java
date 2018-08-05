package com.bootstrappers.gui;

import com.bootstrappers.backend.BootStrappers;
import com.bootstrappers.backend.Member;
import com.bootstrappers.backend.SystemUserAbstract;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;

public class UserLogin extends Application {

    @FXML
    private Button userlogin;
    @FXML
    private Button userregister;
    @FXML
    private TextField loginEmail;
    @FXML
    private TextField loginPassword;
    @FXML
    private TextField regName;
    @FXML
    private TextField regSurname;
    @FXML
    private TextField regEmail;
    @FXML
    private TextField regPhone;
    @FXML
    private TextField regPass;
    private UserScreen userScreen;

    public void initialize() throws Exception {
        userScreen = new UserScreen();
    }

    @FXML
    void userLoginSelected(ActionEvent event) throws Exception {
        MainScreen.member = new Member(null, null, loginPassword.getText(), null, loginEmail.getText(), null);
        if (MainScreen.member.login(MainScreen.bs.getUserArrayList())) {
            for (SystemUserAbstract m : MainScreen.bs.getUserArrayList()) {
                if (m.getEmail().equals(loginEmail.getText())) {
                    MainScreen.member.setName(m.getName());
                    MainScreen.member.setSurname(m.getSurname());
                    MainScreen.member.setEmail(m.getEmail());
                }
            }
            Stage stage = (Stage) userlogin.getScene().getWindow();
            userScreen.start(stage);
        } else {
            MainScreen.showDialog((Stage) userlogin.getScene().getWindow(), "Giriş başarısız!");
        }
    }

    @FXML
    void userRegisterSelected(ActionEvent event) throws Exception {
        MainScreen.member = new Member(regName.getText(), regSurname.getText(), regPass.getText(),
                regPhone.getText(), regEmail.getText(), "com.bootstrappers.backend.Member");

        if (MainScreen.member.registeration(MainScreen.bs.getUserArrayList())) {
            Stage stage = (Stage) userlogin.getScene().getWindow();
            userScreen.start(stage);
        } else {
            MainScreen.showDialog((Stage) userlogin.getScene().getWindow(), "Kullanıcı zaten kayıtlı!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        root.getStylesheets().add(Main.class.getResource("/bootstrap2.css").toExternalForm());
        primaryStage.setTitle("User Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }
}
