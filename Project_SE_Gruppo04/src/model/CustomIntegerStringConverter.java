/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author 39327
 */
public class CustomIntegerStringConverter extends IntegerStringConverter {
    private final IntegerStringConverter converter = new IntegerStringConverter();

    @Override
    public String toString(Integer object) {
        try {
            return converter.toString(object);
        } catch (NumberFormatException e) {
          showAlert(e);
        }
        return null;
    }

    @Override
    public Integer fromString(String string) {
        try {
            return converter.fromString(string);
        } catch (NumberFormatException e) {
           showAlert(e);
        }
        return -1;
    }
    
    private void showAlert(Exception e){
    
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid");
        alert.setHeaderText(null);
        alert.setContentText("The inserted value is invalid. Try again");
        alert.showAndWait();
    }
}

