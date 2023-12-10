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
import java.util.List;
/**
 *
 * @author gruppo_04
 */
public class FileAppendAction extends FileAction{
    // Attributes
    private String append;
    final String DATA_FOLDER_NAME = "data";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + DATA_FOLDER_NAME+ File.separator + "log.txt";
    private String message;
    
    
     // Constructor
    public FileAppendAction(String append, File file) {
        super(file);
        this.append = append;
    }
    
    // Method to get the description of the action
    @Override
    public String getDescription() {
        return "File Append Action of: " + this.append + ". "+ super.toString();    
    }

    // Method to execute the action
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

    
    // Method to log the action into a file
    @Override
    public void log(String filePath) {
        
        
        try {
            
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            // true means append
            FileWriter fileLog = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileLog);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Format the date as a string
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatterData);
            
            // Get the current time
            LocalTime currentTime = LocalTime.now();

            // Format the time as a string
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

    // Other methods not implemented
    @Override
    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Action> getAction() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void removeAction(Action actionToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
