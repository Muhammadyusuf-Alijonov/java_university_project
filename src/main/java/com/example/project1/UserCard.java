package com.example.project1;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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

        setStyle("-fx-border-color: Gray; -fx-border-width: 1px; -fx-background-color: #f0f0f0;");

        // Display user information in a VBox
        VBox userInfoBox = new VBox(5);
        userInfoBox.setPadding(new Insets(5));

        userInfoBox.getChildren().add(new Label("Name: " + user.getFirstName() + " " + user.getSecondName()));
        userInfoBox.getChildren().add(new Label("Passport: " + user.getPassportNum()));
        userInfoBox.getChildren().add(new Label("Email: " + user.getEmail()));
        userInfoBox.getChildren().add(new Label("Reserved Room: " + user.getReservedRoom()));
        userInfoBox.getChildren().add(new Label("Reservation Period: " + user.getReservationPeriod()));

        // Example: Add a button for some action related to the user
        Button actionButton = new Button("Delete customer");
        actionButton.setOnAction(event -> handleActionButton());
        userInfoBox.getChildren().add(actionButton);

        setCenter(userInfoBox);
    }


    private void handleActionButton() {
        Delete.deleteUser(user.getPassportNum());
        controller.refreshList();
    }
}