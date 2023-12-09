/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author gruppo_04
 */
public class ExternalProgramTrigger implements Trigger{

    private final int exit_code;
    private String program;
    private String[] arguments;

    public ExternalProgramTrigger(int exit_code, String program, String[] arguments) {
        this.exit_code = exit_code;
        this.program = program;
        this.arguments = arguments;
    }
    
    @Override
    public boolean check() {
        ProcessBuilder processBuilder = new ProcessBuilder(program);
        
        if(arguments.length != 0){
            processBuilder.command().addAll(Arrays.asList(arguments));
        }
        
        try {
            // Start the program
            Process processo = processBuilder.start();

            // Wait for the process to finish and check if the exit code is the one requested by the user.
            int exit = processo.waitFor();
            return (this.exit_code == exit);
            

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    
    }

    @Override
    public String getDescription() {
         return " Trigger Type: External Program: "+ program + "Exit Value: "+ exit_code ;
     }

    //not implemented method
    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
        
}
