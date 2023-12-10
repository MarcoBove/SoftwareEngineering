/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author schet
 */
public class ExternalProgramExecutionAction implements Action{
     
    private String program;
    private String[] arguments;
    private int exitCode;

    public ExternalProgramExecutionAction(String program, String[] arguments) {
        this.program = program;
        this.arguments = arguments;
        this.exitCode = -99;
    }

    @Override
    public String getDescription() {
        return "Program Execution Action of:  "+ program ;
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getExitCode() {
        return exitCode;
    }
}