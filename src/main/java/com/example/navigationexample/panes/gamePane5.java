package com.example.navigationexample.panes;

import com.example.navigationexample.HelloApplication;
import com.example.navigationexample.IntroScene.creditScene;
import com.example.navigationexample.IntroScene.gameScene3;
import com.example.navigationexample.IntroScene.gameScene5;
import com.example.navigationexample.IntroScene.gameScene6;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class gamePane5 extends BorderPane {
    String hiddenWord;
    String guessedLetters;
    List<Label> letterLabels = new ArrayList<>();
    int incorrectGuessCount = 0;
    int maxIncorrectGuesses = 6;
    Label messageLabel = new Label();
    Label guessesLeftLabel = new Label();

    public gamePane5() {
        // Set up the hidden word
        hiddenWord = "stclair"; // Fixed word "google" for demonstration
        guessedLetters = "";

        // Display the hidden word with blanks for unguessed letters
        HBox lettersBox = new HBox(10);
        lettersBox.setAlignment(Pos.CENTER); // Align letters in the center

        for (int i = 0; i < hiddenWord.length(); i++) {
            Label letterLabel = new Label(hiddenWord.charAt(i) == ' ' ? " " : "_");
            letterLabels.add(letterLabel);
            letterLabel.setAlignment(Pos.CENTER); // Center align the letters
            lettersBox.getChildren().add(letterLabel);
        }

        VBox contentBox = new VBox(20); // Vertical box to hold content
        contentBox.setAlignment(Pos.CENTER); // Align content in the center
        contentBox.getChildren().addAll(lettersBox, createButtonsBox()); // Add letters box and buttons box
        lettersBox.setMargin(contentBox, new Insets(20, 0, 0, 0)); // Add margin to lettersBox

        // Add message labels
        VBox messageBox = new VBox(10);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.getChildren().addAll(messageLabel, guessesLeftLabel);

        this.setCenter(contentBox); // Set content box in the center
        this.setBottom(messageBox); // Set message box at the bottom

        // Add image
        VBox imageBox = new VBox();
        imageBox.setAlignment(Pos.CENTER);
        ImageView imageView = new ImageView(new Image("./images/stclair.png"));
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageBox.getChildren().add(imageView);
        this.setLeft(imageBox); // Set image box on the left

        // Set background color
        this.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, lightBlue 50%);");


        // Bind width and height properties to the stage
        this.prefWidthProperty().bind(HelloApplication.mainStage.widthProperty());
        this.prefHeightProperty().bind(HelloApplication.mainStage.heightProperty());

    }

    private VBox createButtonsBox() {
        VBox buttonsBox = new VBox(10);
        buttonsBox.setAlignment(Pos.CENTER); // Align buttons in the center

        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        HBox rowBox = new HBox(10);
        rowBox.setAlignment(Pos.CENTER);

        for (char c : alphabet) {
            char finalGuessedLetter = c; // Store the guessed letter in a final variable
            Button letterButton = new Button(String.valueOf(c));
            letterButton.setOnAction(e -> handleGuess(finalGuessedLetter)); // Use the final variable inside the lambda expression
            letterButton.setPrefSize(40, 40); // Set button size for visibility
            letterButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white; -fx-border-radius: 10;");
            rowBox.getChildren().add(letterButton);

            if (c == 'm') {
                buttonsBox.getChildren().add(rowBox);
                rowBox = new HBox(10);
                rowBox.setAlignment(Pos.CENTER);
            }
        }

        buttonsBox.getChildren().add(rowBox);

        return buttonsBox;
    }


    private void handleGuess(char guessedLetter) {
        if (!guessedLetters.contains(String.valueOf(guessedLetter))) {
            guessedLetters += guessedLetter;
            boolean guessedCorrectly = hiddenWord.contains(String.valueOf(guessedLetter));
            if (!guessedCorrectly) {
                incorrectGuessCount++;
                displayMessage(guessedLetter);
            }
        }

        // Update the display of the hidden word with guessed letters
        for (int i = 0; i < hiddenWord.length(); i++) {
            Label letterLabel = letterLabels.get(i);
            char letter = hiddenWord.charAt(i);
            if (letter == ' ' || guessedLetters.contains(String.valueOf(letter))) {
                letterLabel.setText(String.valueOf(letter));
            } else {
                letterLabel.setText("_");
            }
        }

        // Check win/loss condition
        if (incorrectGuessCount >= maxIncorrectGuesses) {
            endGame("You lost! The word was: " + hiddenWord.toUpperCase());
        } else if (!getHiddenWord().contains("_")) {
            endGame("Congratulations! You won!");

            //this code is go to the next level
            Button nextLevelButton = new Button("NEXT LEVEL");
            VBox vbox = new VBox(20, nextLevelButton);
            vbox.setAlignment(Pos.BOTTOM_RIGHT);
            nextLevelButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; -fx-background-radius: 55; -fx-border-color: white; -fx-border-width: 2px; -fx-min-width: 120px; -fx-min-height: 40px; -fx-border-radius: 55;");
            nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.mainStage.setScene(new gameScene6());
                }
            });
            this.setRight(vbox); // Add the button to the right side of the pane
        }
    }

    private void displayMessage(char guessedLetter) {
        messageLabel.setText(guessedLetter + " is wrong.");
        int guessesLeft = maxIncorrectGuesses - incorrectGuessCount;
        guessesLeftLabel.setText("You have " + guessesLeft + " guesses left.");

        // Increase font size of messageLabel and guessesLeftLabel
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Example font size: 18
        guessesLeftLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Example font size: 18
    }



    private void endGame(String message) {
        messageLabel.setText(message);
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30)); // Increase font size
        guessesLeftLabel.setText("");
        // Disable all letter buttons
        // Optional: You can add code here to disable the letter buttons if needed
    }

    public String getHiddenWord() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < hiddenWord.length(); i++) {
            char letter = hiddenWord.charAt(i);
            if (letter == ' ' || guessedLetters.contains(String.valueOf(letter))) {
                display.append(letter);
            } else {
                display.append("_");
            }
        }
        return display.toString();
    }
}
