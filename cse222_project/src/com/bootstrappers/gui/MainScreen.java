package com.bootstrappers.gui;

import com.bootstrappers.backend.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

import static com.bootstrappers.gui.MainScreen.addToStack;

public class MainScreen extends Application {

    private AdminLogin adminLoginScreen;
    private UserLogin userLoginScreen;
    public static BootStrappers bs = new BootStrappers();
    private static Stack<Application> screens = new Stack<>();
    public static Member member;
    public static Admin admin;
    public static Graph graph = new Graph();
    public static Home globalHome;
    public static File imageFile;

    @FXML
    private Button adminbutton;
    @FXML
    private Button userbutton;

    public void initialize() throws Exception {
        adminLoginScreen = new AdminLogin();
        userLoginScreen = new UserLogin();
        addToStack(this);
    }

    @FXML
    void adminSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) adminbutton.getScene().getWindow();
        adminLoginScreen.start(stage);
    }

    @FXML
    void userSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        userLoginScreen.start(stage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Main");
        root.getStylesheets().add(Main.class.getResource("/bootstrap2.css").toExternalForm());
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    public static void showDialog(Stage primaryStage, String message) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(message));
        Scene dialogScene = new Scene(dialogVbox, 150, 150);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public static Application backPressed() {
        try {
            screens.pop();
            return screens.pop();
        } catch (EmptyStackException ex) {

        }
        return null;
    }

    public static void addToStack(Application a) {
        Class<?> enclosingClass = a.getClass().getEnclosingClass();
        boolean flag = true;

        Iterator<Application> it = screens.iterator();
        while (it.hasNext()) {
            Application next = it.next();
            if (next instanceof UserLogin || next instanceof UserScreen || next instanceof AdminLogin || next instanceof AdminScreen) {
                flag = false;
            }
        }
        if (flag)
            screens.push(a);
    }
}
