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
 * @author schet
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
        

        // Creare un oggetto ProcessBuilder con il programma e gli argomenti
        ProcessBuilder processBuilder = new ProcessBuilder(program);
        
        if(arguments.length != 0){
            processBuilder.command().addAll(Arrays.asList(arguments));
        }
        
        try {
            // Avviare il processo
            Process processo = processBuilder.start();

            // Attendere che il processo termini
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
            message = "Today " + dateString+ " at " + timeString+ " The \"External Program  Execution Action\" has been executed successfully." ;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeAction(Action actionToRemove) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Action> getAction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}