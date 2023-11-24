/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.control.Alert;

/**
 *
 * @author 39327
 */
public class DisplayMessageAction implements Action{
    private final String message;

    public DisplayMessageAction(String message) {
        this.message = message;
    }
    @Override
    public void execute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DisplayMessageAction");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public String getDescription() {
        return "Display Message Action of: " + this.message;
    }
    
}
