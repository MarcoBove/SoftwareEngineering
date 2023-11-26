/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 39327
 */
public class DisplayMessageAction implements Action{
    private final String message;

    public DisplayMessageAction(String message) {
        this.message = message;
    }
    /*
    It displays the chosen message in an alert page when the rule associated
    with the action is triggered.
    */
    @Override
    public void execute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DisplayMessageAction");
        alert.setHeaderText("Message: ");
        alert.getDialogPane().setContent(new Label(message));
        alert.showAndWait();
        
    }
    
    
    /*
    returns the type of the Action and the specific message chosen
    */
    @Override
    public String getDescription() {
        return "Display Message Action of: " + this.message;
    }
    
}
