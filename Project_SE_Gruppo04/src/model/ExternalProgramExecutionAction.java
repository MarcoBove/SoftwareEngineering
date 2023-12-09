/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 *
 @author gruppo_04
 */
public class ExternalProgramExecutionAction implements Action{
     
    private String program;
    private String[] arguments;
    private int exitCode;
    final String DATA_FOLDER_NAME = "data";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + DATA_FOLDER_NAME+ File.separator + "log.txt";
    private String message;

    public ExternalProgramExecutionAction(String program, String[] arguments) {
        this.program = program;
        this.arguments = arguments;
        this.exitCode = -99;
    }

    @Override
    public String getDescription() {
        return "Program Execution Action of:  " + program ;
    }

    @Override
    public void execute() {
        

        // Create a ProcessBuilder object with the program and arguments.
        ProcessBuilder processBuilder = new ProcessBuilder(program);
        
        if(arguments.length != 0){
            processBuilder.command().addAll(Arrays.asList(arguments));
        }
        
        try {
            // Start the process.
            Process processo = processBuilder.start();

            // Wait for the process to finish.
            exitCode = processo.waitFor();
            
            log(FILE_PATH);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getExitCode() {
        return exitCode;
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

            
            LocalDate currentDate = LocalDate.now();

            // Format the date as a string.
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatterData);
            
            
            LocalTime currentTime = LocalTime.now();

            // Format the time as a string.
            DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = currentTime.format(formatterHour);

            // Append text
            message = "Today " + dateString+ " at " + timeString+ " The \"External Program  Execution Action\" has been executed successfully." ;
            bufferedWriter.write(message);
            bufferedWriter.newLine(); 

            
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void addAction(Action action) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void removeAction(Action actionToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Action> getAction() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}