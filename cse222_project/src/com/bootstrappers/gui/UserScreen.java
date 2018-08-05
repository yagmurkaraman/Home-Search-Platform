package com.bootstrappers.gui;

import com.bootstrappers.backend.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.bootstrappers.gui.MainScreen.addToStack;
import static com.bootstrappers.gui.MainScreen.backPressed;

public class UserScreen extends Application implements Initializable {

    private AddAdvertisementUser addAdvertisement;
    private selectAdvertisementUser selectAdvertisement;
    @FXML
    private TextField minPrice;
    @FXML
    private TextField maxPrice;
    @FXML
    private TextField distance;
    @FXML
    private Button addadv;
    @FXML
    private Button addadv2;
    @FXML
    private TableView usertable;
    @FXML
    private ComboBox locationbox;
    @FXML
    private ComboBox roomsbox;
    @FXML
    private ComboBox statusbox;
    @FXML
    private ComboBox typebox;
    @FXML
    private Label welcometext;
    @FXML
    private ImageView logo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addAdvertisement = new AddAdvertisementUser();
        selectAdvertisement = new selectAdvertisementUser();
        fillTable(true,false, null, null);
        locationbox.getItems().removeAll(locationbox.getItems());
        locationbox.getItems().addAll("--All--", "Adalar", "Arnavutkoy", "Atasehir", "Avcilar", "Bagcilar", "Bahcelievler", "Bakirkoy", "Bayrampasa", "Besiktas", "Beykoz", "Beylikduzu",
                "Beyoglu", "Buyukcekmece", "Catalca", "Cekmekoy","Esenler","Esenyurt","Eyup","Fatih","Gaziosmanpasa","Gungoren","Kadikoy",
                "Kagithane","Kartal","Maltepe","Pendik","Sariyer","Sisli","Tuzla","Uskudar");
        locationbox.getSelectionModel().select("--All--");

        roomsbox.getItems().removeAll(roomsbox.getItems());
        roomsbox.getItems().addAll("--All--", "1+0", "1+1", "2+1", "3+1", "4+1");
        roomsbox.getSelectionModel().select("--All--");

        statusbox.getItems().removeAll(statusbox.getItems());
        statusbox.getItems().addAll("--All--", "Rent", "Sale");
        statusbox.getSelectionModel().select("--All--");

        typebox.getItems().removeAll(typebox.getItems());
        typebox.getItems().addAll("--All--", "Apartment", "House", "Residence", "Dublex");
        typebox.getSelectionModel().select("--All--");
        welcometext.setText("Welcome " + MainScreen.member.getEmail());

        File f = new File("home_images\\logo.jpeg");
        System.out.println("Loading image from: " + f.getAbsolutePath());
        Image img = new Image("file:///" + f.getAbsolutePath());
        logo.setImage(img);

        addToStack(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserScreen.fxml"));
        root.getStylesheets().add(Main.class.getResource("/bootstrap2.css").toExternalForm());
        primaryStage.setTitle("User");
        primaryStage.setScene(new Scene(root, 1300, 600));
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }

    private void fillTable(boolean clear_filters, boolean clear, ArrayList homelist_external, ArrayList<Integer> homelist_external2) {
        BootStrappers bs = new BootStrappers();
        ArrayList<Home> homelist = bs.getHomeArrayList();
        ArrayList<Home> lasthomelist = new ArrayList<>();
        ArrayList<Home> lastlasthomelist = new ArrayList<>();
        ArrayList<Home> distancelist = new ArrayList<>();
        ArrayList<Home> typeboxlist = new ArrayList<>();
        ArrayList<Home> roomsboxlist = new ArrayList<>();
        ArrayList<Home> statusboxlist = new ArrayList<>();
        ArrayList<Home> locationboxlist = new ArrayList<>();
        ArrayList<Home> pricelist = new ArrayList<>();

        if (clear_filters) {
            lastlasthomelist.addAll(homelist);
        } else {
            if (!typebox.getValue().toString().equals("--All--")) {
                for (Home h : homelist) {
                    if (h.getType().equals(typebox.getValue().toString())) {
                        typeboxlist.add(h);
                    }
                }
            } else {
                typeboxlist.addAll(homelist);
            }

            if (!roomsbox.getValue().toString().equals("--All--")) {
                for (Home h : homelist) {
                    if (h.getCountOfRoom().equals(roomsbox.getValue().toString())) {
                        roomsboxlist.add(h);
                    }
                }
            } else {
                roomsboxlist.addAll(homelist);
            }

            if (!statusbox.getValue().toString().equals("--All--")) {
                for (Home h : homelist) {
                    if (h.getStatus().equals(statusbox.getValue().toString())) {
                        statusboxlist.add(h);
                    }
                }
            } else {
                statusboxlist.addAll(homelist);
            }

            if (!locationbox.getValue().toString().equals("--All--")) {
                for (Home h : homelist) {
                    if (h.getAddress().getNeighborhood().equals(locationbox.getValue().toString())) {
                        locationboxlist.add(h);
                    }
                }
            } else {
                locationboxlist.addAll(homelist);
            }

            if (!minPrice.getText().isEmpty() && !maxPrice.getText().isEmpty()) {
                try {
                    for (Home h : homelist) {
                        if (Integer.parseInt(minPrice.getText()) <= h.getPrice() && Integer.parseInt(maxPrice.getText()) >= h.getPrice()) {
                            pricelist.add(h);
                        }
                    }
                } catch (NumberFormatException nfe) {
                }
            } else {
                pricelist.addAll(homelist);
            }

            if (distance.getText().length() > 0) {
                distancelist = MainScreen.graph.DepthFirstSearch(Integer.parseInt(distance.getText()));
                if (distancelist == null) {
                    distancelist = new ArrayList<>();
                    distancelist.addAll(homelist);
                }
            } else {
                distancelist.addAll(homelist);
            }

            lastlasthomelist = intersect(typeboxlist, roomsboxlist);
            lastlasthomelist = intersect(lastlasthomelist, statusboxlist);
            lastlasthomelist = intersect(lastlasthomelist, locationboxlist);
            lastlasthomelist = intersect(lastlasthomelist, pricelist);
            lastlasthomelist = intersect(lastlasthomelist, distancelist);
        }

        ObservableList data;
        if (homelist_external2 != null) {
            ArrayList list = new ArrayList();
            for (Integer i : homelist_external2) {
                for (Home h : MainScreen.bs.getHomeArrayList()) {
                    if (h.getAdNumber() == i)
                        list.add(h);
                }
            }
            data = FXCollections.observableList(list);
        } else if (homelist_external != null){
            data = FXCollections.observableList(homelist_external);
        } else {
            data = FXCollections.observableList(lastlasthomelist);
        }

        usertable.setItems(data);

        TableColumn col1 = new TableColumn("Ad Number");
        col1.setCellValueFactory(new PropertyValueFactory("adNumber"));
        TableColumn col2 = new TableColumn("Owner");
        col2.setCellValueFactory(new PropertyValueFactory("owner"));
        TableColumn col3 = new TableColumn("Location");
        col3.setCellValueFactory(new PropertyValueFactory("address"));
        TableColumn col4 = new TableColumn("Price");
        col4.setCellValueFactory(new PropertyValueFactory("price"));
        TableColumn col5 = new TableColumn("Rooms");
        col5.setCellValueFactory(new PropertyValueFactory("countOfRoom"));
        TableColumn col6 = new TableColumn("Status");
        col6.setCellValueFactory(new PropertyValueFactory("status"));
        TableColumn col7 = new TableColumn("Type");
        col7.setCellValueFactory(new PropertyValueFactory("type"));
        TableColumn col8 = new TableColumn("Favorite");
        col8.setCellValueFactory(new PropertyValueFactory("type"));

        usertable.getColumns().setAll(col1, col2, col3, col4, col5, col6, col7);
        usertable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        usertable.setRowFactory( tv -> {
            TableRow<Home> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Home home = row.getItem();
                    MainScreen.globalHome = home;

                    Stage stage = (Stage) addadv.getScene().getWindow();
                    try {
                        selectAdvertisement.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    @FXML
    void applyFiltersSelected(ActionEvent event) throws Exception {
        fillTable(false, true, null, null);
    }

    @FXML
    void clearFiltersSelected(ActionEvent event) throws Exception {
        fillTable(true, true, null, null);
    }

    @FXML
    void addAdvSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) addadv.getScene().getWindow();
        addAdvertisement.start(stage, 0);
    }

    @FXML
    void showMineSelected(ActionEvent event) throws Exception {
        fillTable(false,true, MainScreen.bs.ownerHome(MainScreen.member), null);
    }

    @FXML
    void backButtonSelected(ActionEvent event) throws Exception {
        Stage stage = (Stage) addadv.getScene().getWindow();
        backPressed().start(stage);
    }

    @FXML
    void showFavSelected(ActionEvent event) throws Exception {
        MainScreen.member.readFavorites();
        fillTable(false,true, null, MainScreen.member.getFavorites());
    }

    private ArrayList<Home> intersect(ArrayList<Home> one, ArrayList<Home> two) {
        one.retainAll(two);
        return one;
    }
}
