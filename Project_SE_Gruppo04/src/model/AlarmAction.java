/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author 39327
 */
public class AlarmAction implements Action{
    private final File audio;

    public AlarmAction(File audio) {
        this.audio = audio;
    }

    /*
    It plays an audio file in a dialog window and allows its closure by pressing the button
    */
    @Override
    public void execute() {
        Stage stage = new Stage();
        stage.setTitle("Alarm Action");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        if(this.audio.exists()){
            Button stopButton = new Button("Stop");
            stopButton.setPrefSize(120, 45);
            vbox.getChildren().add(stopButton);
            Media media = new Media(this.audio.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); 
            mediaPlayer.play();
            stopButton.setOnAction(stopEvent -> {
               mediaPlayer.stop();
               stage.close();
            });
            //when the window closes the audio file stops
            stage.setOnCloseRequest(windowEvent -> {
                mediaPlayer.stop();
            });
        }
        else{
            vbox.getChildren().add(new Label("Audio file not exist"));
        }
        stage.setScene(new Scene(vbox, 300, 200));
        stage.show();
    }

    /*
    returns the type and the specific file audio chosen
    */
    @Override
    public String getDescription() {
        return "Alarm Action:  " + this.audio.getName();
    }

    
}
