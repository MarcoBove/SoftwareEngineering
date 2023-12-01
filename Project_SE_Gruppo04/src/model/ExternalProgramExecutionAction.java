/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;

/**
 *
 * @author schet
 */
public class ExternalProgramExecutionAction implements Action{
     
    private String program;
    private String[] arguments;
    public int testExecute;

    public ExternalProgramExecutionAction(String program, String[] arguments) {
        this.program = program;
        this.arguments = arguments;
    }
    
    

    @Override
    public String getDescription() {
        return "Program Execution Action of:  "+ program ;
    }

    @Override
    public void execute() {
        /*try {
            // Crea un oggetto ProcessBuilder con il comando fornito come parametro
            ProcessBuilder processBuilder = new ProcessBuilder(program);

            // Esegui il processo
            Process processo = processBuilder.start();
            
            
            if(processBuilder.start().isAlive() == true ){ 
                testExecute =0; 
            }
            


        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}
