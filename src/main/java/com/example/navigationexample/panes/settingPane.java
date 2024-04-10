package com.example.navigationexample.panes;

import com.example.navigationexample.HelloApplication;
import com.example.navigationexample.IntroScene.mainScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

import static com.example.navigationexample.HelloApplication.mainStage;

public class settingPane extends BorderPane {

    private MediaPlayer mediaPlayer;

    public settingPane(MediaPlayer mediaPlayer) {
        // Initialize settingPane with the provided MediaPlayer
        this.mediaPlayer = mediaPlayer;

    }
    public settingPane() {
        Image img = new Image("./images/tropical-background.jpeg");
        ImageView imageView = new ImageView(img);
        imageView.fitHeightProperty().bind(mainStage.heightProperty());
        imageView.fitWidthProperty().bind(mainStage.widthProperty());

        // Create a rectangle
        Rectangle rectangle = new Rectangle(300, 500, Color.LIGHTBLUE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeWidth(1);

        // Add the background image and rectangle
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, rectangle);
        // Add your text nodes, buttons, etc. to the contentBox
        VBox contentBox = new VBox();


        // Add your content on top of the background image and rectangle
        stackPane.getChildren().add(contentBox);

        // Set the stackPane as the center of the BorderPane
        this.setCenter(stackPane);

        // Add title to the center of the BorderPane
        Text title = new Text("WORDSPACE");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 48));
        title.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);");
        StackPane.setAlignment(title, Pos.TOP_CENTER);


        // Toggle button to turn on and off the background music
        ToggleButton onBotton = new ToggleButton("Play");
        ToggleButton offBotton = new ToggleButton("Pause");
        ToggleGroup toggleGroup = new ToggleGroup();
        onBotton.setToggleGroup(toggleGroup);
        offBotton.setToggleGroup(toggleGroup);
        Text textbg = new Text("bgmusic");

        onBotton.setOnAction(event -> {
            introPane.startMusic();
        });

        offBotton.setOnAction(event -> {
            introPane.stopMusic();
        });
        // HBox to hold buttons
        HBox buttonStack = new HBox();
        buttonStack.getChildren().addAll(textbg, onBotton, offBotton);
        buttonStack.setAlignment(Pos.CENTER);
        buttonStack.setSpacing(5);

        // StackPane to hold rectangle and buttonStack
        StackPane rectangleStack = new StackPane();
        rectangleStack.getChildren().addAll(rectangle, buttonStack); // Add rectangle and toggle button to the stackPane
        StackPane.setAlignment(rectangleStack, Pos.CENTER); // Set alignment of rectangleStack to center

        // Create a back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");
        backButton.setOnAction(event -> {
            // Handle back action
            HelloApplication.mainStage.setScene(new mainScene()); // Return to main scene
        });

        // Add the back button to an HBox and align it to the top left
        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        // Add the rectangleStack to the stackPane
        stackPane.getChildren().addAll(rectangleStack,topBar);

        // Set the stackPane as the center of the BorderPane
        this.setCenter(stackPane);
    }
}
