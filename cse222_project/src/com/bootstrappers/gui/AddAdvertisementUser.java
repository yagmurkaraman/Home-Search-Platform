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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.bootstrappers.gui.MainScreen.addToStack;

public class AddAdvertisementUser extends Application implements Initializable {

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
    @FXML
    private Button imageselect;
    @FXML
    private TextField price;
    @FXML
    private TextField area;

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
        Parent root = FXMLLoader.load(getClass().getResource("AddAdvertisementUser.fxml"));
        primaryStage.setTitle("Add Advertisement");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        addToStack(this);
    }

    public void start(Stage primaryStage, int ret) throws IOException {
        this.ret = ret;
        start(primaryStage);
    }

    @FXML
    void addButtonSelected(ActionEvent event) throws Exception {
        int adnumber = MainScreen.bs.generateAdNumber();
        Home newHome = new Home(roomsbox.getValue().toString(), statusbox.getValue().toString(),
                typebox.getSelectionModel().getSelectedItem().toString(),
                new Address("Istanbul","Turkey",locationbox.getSelectionModel().getSelectedItem().toString()),
                adnumber, MainScreen.member, Integer.parseInt(area.getText()), Integer.parseInt(price.getText()), 100);

        System.out.println(MainScreen.member.getEmail());
        MainScreen.bs.addHome(newHome);
        if (MainScreen.imageFile != null) {
            File dest = new File("./home_images/" + adnumber + ".jpg");
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(MainScreen.imageFile);
                os = new FileOutputStream(dest);
                System.out.println("Input path : " + MainScreen.imageFile.getAbsolutePath() + " Output path: " + dest.getAbsolutePath());
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } finally {
                if (is != null && os!= null) {
                    is.close();
                    os.close();
                }
            }
            MainScreen.imageFile = null;
        }

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
        us.start(stage);
    }

    @FXML
    void imageButtonSelected(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Image");
        MainScreen.imageFile = chooser.showOpenDialog(new Stage());
    }
}
