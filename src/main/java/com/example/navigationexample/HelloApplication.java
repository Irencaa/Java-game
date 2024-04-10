package com.example.navigationexample;

import com.example.navigationexample.IntroScene.introScene;
import com.example.navigationexample.panes.introPane;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class HelloApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setTitle("Hello");

        // Create MediaPlayer instance
        Media backMusic = new Media(new File("./music/background-music.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(backMusic);

        // Set MediaPlayer instance in introPane
        introPane.setMediaPlayer(mediaPlayer);

        // Set the introScene
        mainStage.setScene(new introScene());
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
