/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
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

    @Override
    public void execute() {
        Stage stage = new Stage();
        if(this.audio.exists()){
         Media media = new Media(this.audio.toURI().toString());
         MediaPlayer mediaPlayer = new MediaPlayer(media);
         mediaPlayer.play();
         stage.setTitle("Audio Player");
         stage.show();
        }
    }

    @Override
    public String getDescription() {
        return "Alarm Action " + this.audio.getName();
    }

    
}
