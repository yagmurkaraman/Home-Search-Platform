package com.bootstrappers.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static com.bootstrappers.gui.MainScreen.addToStack;

public class selectAdvertisementUser extends Application implements Initializable{

    private int ret = 0;
    private UserScreen us;
    @FXML
    ImageView imagee;
    @FXML
    Button favorite;
    @FXML
    Label priceboxsel;
    @FXML
    Label locationboxsel;
    @FXML
    Label roomsboxsel;
    @FXML
    Label statusboxsel;
    @FXML
    Label typeboxsel;
    @FXML
    Label ownermail;
    @FXML
    Label ownerphone;
    @FXML
    Label ownername;
    @FXML
    GridPane gridpane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("selectAdvertisementUser.fxml"));
        root.getStylesheets().add(Main.class.getResource("/bootstrap2.css").toExternalForm());
        primaryStage.setTitle("Select Advertisement");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
        addToStack(this);
    }

    @FXML
    void favoriteSelected(ActionEvent event) throws Exception {
        if (favorite.getText().equals("Favorite")) {
            MainScreen.member.saveHome(MainScreen.globalHome.getAdNumber());
            favorite.setText("Unfavorite");
        } else {
            MainScreen.member.deleteFav(MainScreen.globalHome.getAdNumber());
            favorite.setText("Favorite");
        }
    }

    @FXML
    void addButtonSelected(ActionEvent event) throws Exception {
    }

    @FXML
    void cancelButtonSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) favorite.getScene().getWindow();
        us.start(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File f = new File("home_images\\" + MainScreen.globalHome.getAdNumber() + ".jpg");
        System.out.println("Loading image from: " + f.getAbsolutePath());
        Image img = new Image("file:///" + f.getAbsolutePath());
        imagee.setImage(img);

        MainScreen.member.readFavorites();
        favorite.setText(MainScreen.member.getFavorites().indexOf(MainScreen.globalHome.getAdNumber()) == -1 ? "Favorite" : "Unfavorite");

        priceboxsel.setText(MainScreen.globalHome.getPrice() + "");
        locationboxsel.setText(MainScreen.globalHome.getAddress().getNeighborhood() + "/" + MainScreen.globalHome.getAddress().getProvince());
        roomsboxsel.setText(MainScreen.globalHome.getCountOfRoom());
        statusboxsel.setText(MainScreen.globalHome.getStatus());
        typeboxsel.setText(MainScreen.globalHome.getType());
        if (MainScreen.globalHome.getOwner() != null) {
            ownermail.setText(MainScreen.globalHome.getOwner().getEmail());
            ownerphone.setText(MainScreen.globalHome.getOwner().getPhoneNumber());
            ownername.setText(MainScreen.globalHome.getOwner().getName() + MainScreen.globalHome.getOwner().getSurname());
        } else {
            ownermail.setText("");
            ownerphone.setText("");
            ownername.setText("");
        }
        us = new UserScreen();
    }
}
