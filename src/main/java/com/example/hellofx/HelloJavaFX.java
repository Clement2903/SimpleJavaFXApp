package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {
    @Override
    public void start(Stage stage) {

        Label message = new Label("Welcome, CLEMENT MWABA CHILUFYA!");


        Button startButton = new Button("Start");


        Button resetButton = new Button("Reset");


        startButton.setOnAction(event ->
                message.setText("Great! You clicked the button.")
        );


        resetButton.setOnAction(event ->
                message.setText("Welcome, CLEMENT MWABA CHILUFYA!")
        );


        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton, resetButton);


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(message, buttonBox);

        Scene scene = new Scene(layout, 500, 300);


        stage.setTitle("My First JavaFX Application - 20240439");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}