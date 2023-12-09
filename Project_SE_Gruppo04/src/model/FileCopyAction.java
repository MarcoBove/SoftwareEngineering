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
 * @author gruppo_04
 */
public class FileCopyAction extends FileAction {
    
   
    // Attributes
    private final File selectedDirectory;
    final String DATA_FOLDER_NAME = "data";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + DATA_FOLDER_NAME+ File.separator + "log.txt";
    private String message;
    private int count =0;
    
    
    // Constructor
    public FileCopyAction (File selectedFile, File selectedDirectory){
        
       super(selectedFile);
       this.selectedDirectory=selectedDirectory;
       
    }
    
    // Method to get the description of the action
    @Override
    public String getDescription() {
        return super.toString() + "       copied into:        " + selectedDirectory;   
    }

    // Method to execute the action
    @Override
    public void execute() {
        
     // Check if both the source file and the destination directory are not null
        if (super.getFile() != null && selectedDirectory != null) {

            Path source = Paths.get(super.getFile().getAbsolutePath());
            Path destination = Paths.get(selectedDirectory.getAbsolutePath());
            int count_file = 0;
            try {
                Path fileToCopy = destination;    
                // Check for existing files in the destination directory
                while (Files.exists(fileToCopy)) {
                    count_file++;
                    // Prepare the new file name based on the count of existing files
                    String newName = String.format(selectedDirectory.getAbsolutePath());
                    String file = source.getFileName().toString();

                    String[] newName2 = file.split("\\.");

                    String finalName = newName+File.separator+newName2[0]+"("+count_file+")."+newName2[1];

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

            // Format the date as a string.
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatterData);
            
            // Get the current time
            LocalTime currentTime = LocalTime.now();

            // Format the time as a string.
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
