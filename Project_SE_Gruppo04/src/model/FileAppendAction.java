/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
/**
 *
 * @author Andre
 */
public class FileAppendAction extends FileAction{

    private String append;

    public FileAppendAction(String append, File file) {
        super(file);
        this.append = append;
    }
    
    @Override
    public String getDescription() {
        return "File Append Action of:  " + this.append + ". "+ super.toString();
        }

    @Override
    public void execute() {
        try {
            // true means append
            FileWriter fileWriter = new FileWriter(super.getFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Append text
            bufferedWriter.write(append);
            bufferedWriter.newLine();  // Aggiungi una nuova riga se necessario

            // Close BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FileAppendAction");
        alert.setHeaderText("Append successful");
        alert.getDialogPane().setContent(new Label(this.getDescription()));
        alert.showAndWait();
    }

    

    
    
    
}
