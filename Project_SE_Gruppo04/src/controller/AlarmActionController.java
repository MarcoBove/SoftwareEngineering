/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author gruppo_04
 */

//this class implement Observer interface
public class AlarmActionController implements Observer {
    
    // This method is called when the observed object is updated, then call playAlarm method
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof File) {
            File file = (File) arg;
            Platform.runLater(() -> playAlarm(file));
        }
    }

    //it plays the audio file and wait for the user's response
    private void playAlarm(File file) {
        Stage stage = new Stage();
        stage.setTitle("Alarm Action");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Button stopButton = new Button("Stop");
        stopButton.setPrefSize(120, 45);
        vbox.getChildren().add(stopButton);

        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        stopButton.setOnAction(stopEvent -> {
            mediaPlayer.stop();
            stage.close();
        });

        stage.setOnCloseRequest(windowEvent -> {
            mediaPlayer.stop();
        });

        stage.setScene(new Scene(vbox, 300, 200));
        stage.show();
    }
}
