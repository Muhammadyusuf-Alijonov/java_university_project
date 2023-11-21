package com.example.project1;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.List;
import java.util.function.ToIntFunction;

public class UserCardFX extends Application {


    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField passportField = new TextField();
    private final TextField reservation_period = new TextField();
    private final TextField Client_email = new TextField();
    private final TextField reserved_room = new TextField();
    private final TextField delete_user = new TextField();


    @FXML
    private ListView<String> CardLV;
    @FXML
    private  Button addCard;
    @FXML
    private Button deleteCard;

    List<UserData> userList = FetchFromDB.fetchUserData();

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("User Card Manager");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UserCardFX.class.getResource("hello-view.fxml"));
            Scene fmx_scene = new Scene(fxmlLoader.load());

            // Assuming that the controller is set in the FXML file, you can retrieve it like this:
            UserCardFX controller = fxmlLoader.getController();

            if (controller.CardLV == null) {
                System.err.println("Error: CardLV is null");
            }


            for (UserData user : userList) {
                String userD = user.displayUser();
                controller.CardLV.getItems().add(userD);
            }


            Scene scene = fmx_scene;
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshList(){
        CardLV.getItems().clear(); // Clear the existing items in the ListView
        List<UserData> userList = FetchFromDB.fetchUserData();
        for (UserData user : userList) {
            String UserD = user.displayUser();
            CardLV.getItems().add(UserD);
        }
    }

    public void addUserCard() {


        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
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

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> primaryStage.close());
        grid.add(cancelButton, 1, 6);

        GridPane.setColumnIndex(okButton, 1);
        GridPane.setColumnIndex(cancelButton, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleOkButton() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String passport = passportField.getText();
        String period = reservation_period.getText();
        String email = Client_email.getText();
        String room = reserved_room.getText();

        UserData new_user = new UserData(firstName, lastName, passport, email, Integer.parseInt(room), period);

        AddToDB.insertIntoDB(new_user);

        // Add the new_user directly to the ListView
        refreshList();
        // Close the stage (dialog)
        ((Stage) firstNameField.getScene().getWindow()).close();

    }

    public void deleteSelected() {

        Stage primaryStage = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Delete user"), 0, 0);
        grid.add(delete_user, 1, 0);

        Button okButton = new Button("Delete");
        okButton.setOnAction(e -> handleDeleteButton());
        grid.add(okButton, 0, 2);

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> primaryStage.close());
        grid.add(cancelButton, 1, 2);

        GridPane.setColumnIndex(okButton, 1);
        GridPane.setColumnIndex(cancelButton, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText(null);
//            alert.setContentText("No user cards selected.");
//            alert.showAndWait();
//        }
    }

    private void handleDeleteButton() {
        Delete.deleteUser(delete_user.getText());
        refreshList();
        ((Stage) delete_user.getScene().getWindow()).close();
    }

}