/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author 39327
 */
public class FileCopyAction extends FileAction {
    
   
    private final File selectedDirectory;
    final String DATA_FOLDER_NAME = "data";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + DATA_FOLDER_NAME+ File.separator + "log.txt";
    private String message;
    private int count =0;
    
    public FileCopyAction (File selectedFile, File selectedDirectory){
       super(selectedFile);
       this.selectedDirectory=selectedDirectory;
    }
    @Override
    public String getDescription() {
     return super.toString() + "       copied into:        " + selectedDirectory;   
    }

    @Override
    public void execute() {
    if (super.getFile() != null && selectedDirectory != null) {
            Path source = Paths.get(super.getFile().getAbsolutePath());
            Path destination = Paths.get(selectedDirectory.getAbsolutePath());
            int count_file = 0;
            try {
                
                    Path fileToCopy = destination;    
                     
                    while (Files.exists(fileToCopy)) {
                        count_file++;
                        String newName = String.format(selectedDirectory.getAbsolutePath());
                        String file = source.getFileName().toString();
                        
                        String[] newName2 = file.split("\\.");
                    
                        String finalName = newName+"\\"+newName2[0]+"("+count_file+")."+newName2[1];
                    
                        fileToCopy = destination.resolve(finalName);
                        
                    }
                    Files.copy(source, fileToCopy);
                
                
                    log(FILE_PATH);
                
            } catch (IOException e) {
                System.err.println("Error " + e.getMessage());
            }
        } else {
            System.out.println(" aborted");
        }      
    }

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
            message = "Today " + dateString+ " at " + timeString+ " The \"File Copy Action\" has been executed successfully." ;
            bufferedWriter.write(message);
            bufferedWriter.newLine(); 

            // Close BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Action> getAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void removeAction(Action actionToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
