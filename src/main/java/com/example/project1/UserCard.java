package com.example.project1;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class UserCard extends BorderPane {

    private final UserData user;
    private final UserCardFX controller;

    public UserCard(UserData user, UserCardFX controller) {
        this.user = user;
        this.controller = controller;

        // Customize the appearance of the user card
        setMinWidth(200);
        setPrefWidth(280);
        setMaxWidth(500);
        setMinHeight(200);
        setPrefHeight(200);
        setMaxHeight(400);
        setPadding(new Insets(20));

        // Here we customize the style of the user cards
        setStyle("-fx-border-color: Gray; -fx-border-width: 2px; -fx-background-color: #f0f0f0; -fx-background-radius: 10; -fx-border-radius: 10; -fx-font-family: sans-serif;");

        // Display user information in a VBox
        VBox userInfoBox = new VBox(5);
        userInfoBox.setPadding(new Insets(5));

        // showing the data of user fetched from database in user cards
        userInfoBox.getChildren().add(new Label("Name: " + user.getFirstName() + " " + user.getSecondName()));
        userInfoBox.getChildren().add(new Label("Passport: " + user.getPassportNum()));
        userInfoBox.getChildren().add(new Label("Email: " + user.getEmail()));
        userInfoBox.getChildren().add(new Label("Reserved Room: " + user.getReservedRoom()));
        userInfoBox.getChildren().add(new Label("Reservation Period: " + user.getReservationPeriod()));

        // Here we create button Delete user for all user cards to easily delete the user card and user information from the database
        Button actionButton = new Button("Delete customer");
        actionButton.setOnAction(event -> handleActionButton());
        userInfoBox.getChildren().add(actionButton);


        actionButton.setStyle("-fx-background-color: #f95959; -fx-text-fill: White;");
        // centering the outputting data inside the user card
        setCenter(userInfoBox);
    }

    // simple function to handle action button "Delete user" inside the user cards
    private void handleActionButton() {
        Delete.deleteUser(user.getPassportNum());
        controller.refreshList();
    }
}
