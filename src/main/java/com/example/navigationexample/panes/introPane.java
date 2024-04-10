package com.example.navigationexample.panes;

import com.example.navigationexample.HelloApplication;
import com.example.navigationexample.IntroScene.mainScene;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class introPane extends BorderPane {

    // Declaring a static MediaPlayer object
    private static MediaPlayer mediaPlayer;

    // Constructor taking a MediaPlayer object as parameter
    public introPane(MediaPlayer player) {
        // Assigning the MediaPlayer object passed as parameter to the static variable
        mediaPlayer = player;
    }

    // Method to start playing music
    public static void startMusic() {
        // Checking if the MediaPlayer object is not null
        if (mediaPlayer != null) {
            // Playing the media
            mediaPlayer.play();
        }
    }

    // Method to stop playing music
    public static void stopMusic() {
        // Checking if the MediaPlayer object is not null
        if (mediaPlayer != null) {
            // Pausing the media
            mediaPlayer.pause();
        }
    }

    // Default constructor
    public introPane(){
        // Creating a Media object with the path to the background music file
        Media backMusic = new Media(new File("./music/background-music.mp3").toURI().toString());

        // Creating a MediaPlayer object with the background music
        mediaPlayer = new MediaPlayer(backMusic);
        // Setting the cycle count to indefinite (continuous play)
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // Playing the media
        mediaPlayer.play();

        // Creating an Image object with the path to the background image file
        Image img = new Image("./images/tropical-background.jpeg");
        // Creating an ImageView with the image
        ImageView imageView = new ImageView(img);
        // Binding image height to stage height, // Binding image width to stage width
        imageView.fitHeightProperty().bind(HelloApplication.mainStage.heightProperty());
        imageView.fitWidthProperty().bind(HelloApplication.mainStage.widthProperty());

        // Creating a StackPane for stacking nodes on top of each other
        StackPane stackPane = new StackPane();

        // Creating a Text object for the intro title
        Text introTitle = new Text("WORDSPACE");

        // Creating a Button object
        Button welcomeButton = new Button("Welcome");
        // Setting style for the button
        welcomeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");

        // Setting font and color for the intro title
        introTitle.setFont(Font.font("Brush Script MT", FontWeight.BOLD, 48));
        introTitle.setFill(Color.WHITE);
        // Setting opacity to blend with background
        introTitle.setOpacity(0.8);

        // Setting the initial position of the title above the screen
        introTitle.setTranslateY(-300);

        // Creating a FadeTransition for the title
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), introTitle);
        // Setting initial opacity
        fadeTransition.setFromValue(0);
        // Setting final opacity
        fadeTransition.setToValue(1);

        // Creating a TranslateTransition for moving the title to its final position
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), introTitle);
        // Setting final Y position
        translateTransition.setToY(-160);

        // Creating a ParallelTransition to run both transitions together
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        // Starting the parallel transition
        parallelTransition.play();

        // Adding the image, introTitle, and welcomeButton to the stackPane
        stackPane.getChildren().addAll(imageView, introTitle, welcomeButton);

        // Setting stackPane as the center of the BorderPane
        this.setCenter(stackPane);

        // Setting event handler for welcomeButton
        welcomeButton.setOnMouseClicked(event -> {
            // Setting the scene to mainScene
            HelloApplication.mainStage.setScene(new mainScene());
        });
    }

    // Setter method for MediaPlayer
    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
    }
}