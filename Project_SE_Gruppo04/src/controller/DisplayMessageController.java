/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 *
 * @author gruppo_04
 */
//this class implement Observer interface
public class DisplayMessageController implements Observer {
    
    //This method is called when the observed object is updated, then display the message on the screen, waiting for the user's response
    @Override
    public void update(Observable o, Object arg) {
        
        String message = (String) arg;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DisplayMessageAction");
        alert.setHeaderText("Message: ");
        alert.getDialogPane().setContent(new Label(message));
        alert.showAndWait();
        
    }
}

