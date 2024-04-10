package com.example.navigationexample.panes;


import com.example.navigationexample.HelloApplication;
import com.example.navigationexample.IntroScene.creditScene;
import com.example.navigationexample.IntroScene.gameScene;
import com.example.navigationexample.IntroScene.settingScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

public class mainPane extends BorderPane {


    public mainPane() {
        // Loading the background image
        Image img = new Image("./images/tropical-background.jpeg");
        ImageView imageView = new ImageView(img);
        imageView.fitHeightProperty().bind(HelloApplication.mainStage.heightProperty());
        imageView.fitWidthProperty().bind(HelloApplication.mainStage.widthProperty());

        // Creating a StackPane for stacking nodes
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView); // Add the background image first

        // Creating the title
        Text title = new Text("WORDSPACE");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 48));
        title.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);");
        StackPane.setAlignment(title, Pos.TOP_CENTER);

        // Adding the title to the stackPane
        stackPane.getChildren().add(title);

        // Create the buttons with a VBox
        Button playButton = new Button("START");
        Button creditButton = new Button("CREDIT");
        Button settingButton = new Button("Setting");
        Button exitButton = new Button("QUIT");
        VBox vbox = new VBox(20, playButton,settingButton, creditButton, exitButton);
        vbox.setAlignment(Pos.CENTER);

        // Set the VBox containing buttons to the center of the StackPane
        stackPane.getChildren().add(vbox);

        // Set the stackPane as the center of the BorderPane
        this.setCenter(stackPane);

        // Set the buttons' styles
        playButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");
        creditButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");
        settingButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");

        // Set the buttons' actions
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.mainStage.setScene(new gameScene());
            }
        });

        settingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.mainStage.setScene(new settingScene());
            }
        });

        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.mainStage.setScene(new creditScene());
            }
        });

        exitButton.setOnAction(event -> {
            Platform.exit();
        });


        // Add fade-in/out transitions to all buttons
        addFadeTransition(playButton);
        addFadeTransition(creditButton);
        addFadeTransition(exitButton);
        addFadeTransition(settingButton);
    }

    //method to add fade in and out transition to buttons
    private void addFadeTransition(Button button) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), button);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

}
