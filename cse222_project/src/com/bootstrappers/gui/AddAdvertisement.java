package com.bootstrappers.gui;

import com.bootstrappers.backend.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddAdvertisement extends Application implements Initializable {

    private int ret = 0;
    private UserScreen us;
    private AdminScreen as;
    @FXML
    private Button add;
    @FXML
    private Button cancel;
    @FXML
    private ComboBox locationbox;
    @FXML
    private ComboBox roomsbox;
    @FXML
    private ComboBox statusbox;
    @FXML
    private ComboBox typebox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = new UserScreen();
        as = new AdminScreen();

        locationbox.getItems().removeAll(locationbox.getItems());
        locationbox.getItems().addAll("Pendik", "Kartal", "Sisli", "Maltepe", "Tuzla", "Atasehir", "Bayrampasa", "Atakoy", "Florya");
        locationbox.getSelectionModel().select("Pendik");

        roomsbox.getItems().removeAll(roomsbox.getItems());
        roomsbox.getItems().addAll("1+0", "1+1", "2+1", "3+1", "4+1");
        roomsbox.getSelectionModel().select("1+0");

        statusbox.getItems().removeAll(statusbox.getItems());
        statusbox.getItems().addAll("Rent", "Sale");
        statusbox.getSelectionModel().select("Rent");

        typebox.getItems().removeAll(typebox.getItems());
        typebox.getItems().addAll("Apartment", "House");
        typebox.getSelectionModel().select("Apartment");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddAdvertisement.fxml"));
        primaryStage.setTitle("Add Advertisement");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void start(Stage primaryStage, int ret) throws IOException {
        this.ret = ret;
        start(primaryStage);
    }

    @FXML
    void addButtonSelected(ActionEvent event) throws Exception {
        Home newHome = new Home(roomsbox.getValue().toString(), statusbox.getValue().toString(),
                typebox.getSelectionModel().getSelectedItem().toString(),
                new Address("Istanbul","Turkey",locationbox.getSelectionModel().getSelectedItem().toString()),
                MainScreen.bs.generateAdNumber(), MainScreen.member, 10, 1000, 100);

        MainScreen.bs.addHome(newHome);

        Stage stage = (Stage) add.getScene().getWindow();
        if (ret == 0) {
            us.start(stage);
        } else {
            as.start(stage);
        }
    }

    @FXML
    void cancelButtonSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) add.getScene().getWindow();
        if (ret == 0) {
            us.start(stage);
        } else {
            as.start(stage);
        }
    }
}
