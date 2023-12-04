/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
/**
 *
 * @author Andre
 */
public class FileAppendAction extends FileAction{

    private String append;
    final String DATA_FOLDER_NAME = "data";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + DATA_FOLDER_NAME+ File.separator + "log.txt";
    private String message;
    

    public FileAppendAction(String append, File file) {
        super(file);
        this.append = append;
    }
    
    @Override
    public String getDescription() {
        return "File Append Action of: " + this.append + ". "+ super.toString();
        }

    @Override
    public void execute() {
        try {
            // true means append
            FileWriter fileWriter = new FileWriter(super.getFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Append text
            bufferedWriter.write(append);
            bufferedWriter.newLine(); 

            // Close BufferedWriter
            bufferedWriter.close();
            log(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }   

    @Override
    public void log(String filePath) {
        
        
        try {
            // true means append
            FileWriter fileLog = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileLog);

            // Ottieni la data corrente
            LocalDate currentDate = LocalDate.now();

            // Formatta la data come una stringa
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatterData);
            
            // Ottieni l'ora attuale
            LocalTime currentTime = LocalTime.now();

            // Formatta l'ora come una stringa
            DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = currentTime.format(formatterHour);

            // Append text
            message = "Today " + dateString+ " at " + timeString+ " The \"File Append Action\" has been executed successfully." ;
            bufferedWriter.write(message);
            bufferedWriter.newLine(); 

            // Close BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
