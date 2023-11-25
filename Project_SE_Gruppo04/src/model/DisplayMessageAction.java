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
    @Override
    public void execute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DisplayMessageAction");
        alert.setContentText(message);
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Questo è un dialog personalizzato con un pulsante."));

        Button closeButton = new Button("Chiudi");
        closeButton.setOnAction(a -> {
            // Ottieni la finestra padre del pulsante (l'Alert)
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close(); 
        });

        vbox.getChildren().add(closeButton);

        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }

    @Override
    public String getDescription() {
        return "Display Message Action of: " + this.message;
    }
    
}
