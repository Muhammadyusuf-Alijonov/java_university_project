package com.example.project1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCardFX extends Application {

    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField passportField = new TextField();
    private final TextField reservation_period = new TextField();
    private final TextField Client_email = new TextField();
    private final TextField reserved_room = new TextField();


    @FXML
    private FlowPane userCardsContainer;
    @FXML
    private  Button addCard;

    ResultSet resultSet = FetchFromDB.fetchUserData();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("Hotel Clients Manager");
        File icon = new File("src/main/icons/hotel.png");
        String iconUrl = icon.toURI().toURL().toString();
        primaryStage.getIcons().add(new Image(iconUrl));

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UserCardFX.class.getResource("hello-view.fxml"));
            Scene fmx_scene = new Scene(fxmlLoader.load());

            // Assuming that the controller is set in the FXML file, we can retrieve it like this:
            UserCardFX controller = fxmlLoader.getController();
            SingleUserData user = SingleUserData.getInstance();
            while (true) {
                try{
                    if(!resultSet.next()) break;

                    user.setAll(resultSet.getString("first_name"),resultSet.getString("second_name"),resultSet.getString("passport"),resultSet.getString("email"),resultSet.getInt("reserved_room"),resultSet.getString("reservation_period"));

                    UserCard userCard = new UserCard(controller);
                    controller.userCardsContainer.getChildren().add(userCard);
                    FlowPane.setMargin(userCard,new Insets(10));
                } catch (SQLException e){
                    throw new RuntimeException();
                }
            }

            Scene scene = fmx_scene;
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void refreshList() {
        userCardsContainer.getChildren().clear();
        ResultSet resultSet = FetchFromDB.fetchUserData();
        SingleUserData user = SingleUserData.getInstance();
        while(true){
            try {
                if (!resultSet.next()) break;

                user.setAll(resultSet.getString("first_name"),resultSet.getString("second_name"),resultSet.getString("passport"),resultSet.getString("email"),resultSet.getInt("reserved_room"),resultSet.getString("reservation_period"));

                UserCard userCard = new UserCard(this);
                userCardsContainer.getChildren().add(userCard);
                FlowPane.setMargin(userCard, new Insets(10));


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addUserCard() {


        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);



        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);

        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);

        grid.add(new Label("Passport:"), 0, 2);
        grid.add(passportField, 1, 2);

        grid.add(new Label("Email:"), 0, 3);
        grid.add(Client_email, 1, 3);

        grid.add(new Label("Reserved room:"), 0, 4);
        grid.add(reserved_room, 1, 4);

        grid.add(new Label("Reservation period:"), 0, 5);
        grid.add(reservation_period, 1, 5);


        Button okButton = new Button("Save");
        okButton.setOnAction(e -> handleOkButton());
        grid.add(okButton, 0, 6);

        okButton.setStyle("-fx-background-color: #5585b5; -fx-text-fill: White;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> primaryStage.close());
        grid.add(cancelButton, 1, 6);

        cancelButton.setStyle("-fx-background-color: #f95959; -fx-text-fill: White;");

        GridPane.setColumnIndex(okButton, 1);
        GridPane.setColumnIndex(cancelButton, 2);

        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        addCard.setStyle("-fx-background-color:#00BFFF ;");
    }

    private void handleOkButton() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String passport = passportField.getText();
        String period = reservation_period.getText();
        String email = Client_email.getText();
        String room = reserved_room.getText();

        SingleUserData user = SingleUserData.getInstance();
        user.setAll(firstName, lastName, passport, email, Integer.parseInt(room), period);
        boolean rowAffected = AddToDB.insertIntoDB(user);

        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        if (rowAffected) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Customer added!");
            alert.setTitle("Customer added!");
            alert.setContentText("Customer information is added to database successfully! Feel free to close this window");
            alert.showAndWait();

            refreshList();
            ((Stage) firstNameField.getScene().getWindow()).close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer was Not added!");
            alert.setHeaderText("Customer was Not added!");
            alert.setContentText("Customer information did Not added to database, the customer with the following passport number: " + user.getPassportNum() + " is already exist in database! Please provide valid Customer information!");
            alert.showAndWait();
        }

    }
}