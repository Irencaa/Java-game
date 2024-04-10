package com.example.navigationexample.panes;

import com.example.navigationexample.HelloApplication;
import com.example.navigationexample.IntroScene.mainScene;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class creditPane extends BorderPane {

    public creditPane() {
        // Load background image
        Image img = new Image("./images/black-bg.jpeg");
        ImageView imageView = new ImageView(img);
        imageView.fitHeightProperty().bind(HelloApplication.mainStage.heightProperty());
        imageView.fitWidthProperty().bind(HelloApplication.mainStage.widthProperty());

        // Text elements for developers, resources used, image credits, and music credits
        Text title = new Text("Developers");
        title.setFont(Font.font("Impact", FontPosture.REGULAR, 30));
        title.setFill(Color.WHITE);

        Text developersNames = new Text("Divine Sofowora \nIrene Eweka \nPriyamsinh Veghela ");
        developersNames.setFont(Font.font("Impact", FontPosture.REGULAR, 20));
        developersNames.setFill(Color.WHITE);

        Text resourcesUsed = new Text("Resources used ");
        resourcesUsed.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 25));
        resourcesUsed.setFill(Color.WHITE);

        Text contentResources = new Text("Introduction to Java Programming\nStackOverflow\nYoutube");
        contentResources.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        contentResources.setFill(Color.WHITE);

        Text imagesUsed = new Text("Credit for Images");
        imagesUsed.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 25));
        imagesUsed.setFill(Color.WHITE);

        Text imageResources = new Text("Pinterest\nUnsplash\nPixabay\nGratisography ");
        imageResources.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        imageResources.setFill(Color.WHITE);

        Text creditMusic = new Text("Music Credits");
        creditMusic.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 25));
        creditMusic.setFill(Color.WHITE);

        Text musicSources = new Text("Pixaby \nSound Blocks\nSound Snap\nVoicy\nUpbeat ");
        musicSources.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        musicSources.setFill(Color.WHITE);

        // Apply translate transition to each text element
        applyTranslateTransition(title);
        applyTranslateTransition(developersNames);
        applyTranslateTransition(resourcesUsed);
        applyTranslateTransition(contentResources);
        applyTranslateTransition(imagesUsed);
        applyTranslateTransition(imageResources);
        applyTranslateTransition(creditMusic);
        applyTranslateTransition(musicSources);

        // Create a back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");

        // Apply fade transition to the back button
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(17), backButton);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        // Set action for back button
        backButton.setOnAction(event -> {
            // Handle back action
            HelloApplication.mainStage.setScene(new mainScene()); // Return to main scene
        });

        // VBox to hold text elements and back button
        VBox topBox = new VBox(20); // Increased spacing between elements
        topBox.setAlignment(Pos.CENTER); // Center alignment
        topBox.setPadding(new Insets(50)); // Added padding
        topBox.getChildren().addAll(title, developersNames, resourcesUsed, contentResources, imagesUsed, imageResources, creditMusic,musicSources,backButton);

        // Wrap the image and the vbox in a StackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, topBox);

        // Set the stackPane as the center of the BorderPane
        this.setCenter(stackPane);

    }

    // Method to apply translate transition to text elements
    private void applyTranslateTransition(Text text) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(16), text);
        translateTransition.setFromY(730); // Start from the bottom of the screen
        translateTransition.setToY(-900); // Move text to the top of the screen
        translateTransition.play();
    }
}
