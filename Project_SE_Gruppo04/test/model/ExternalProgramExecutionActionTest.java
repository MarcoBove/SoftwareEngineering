/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author schet
 */
public class ExternalProgramExecutionActionTest {
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String APPLICATION_NAME = "Test1.jar";
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME+ File.separator + "test.txt";
    final String ARG1 = "prova1";
    final String ARG2 = "test1";
    private ExternalProgramExecutionAction action;
    private String program;
    private String[] arguments;
    private File sourceFile;
    
    
    @Before
    public void setup() throws IOException{
        sourceFile = new File(FILE_PATH);
        if(!sourceFile.exists())
            sourceFile.createNewFile();
        program = "java"; 
        arguments = new String[]{"-jar",LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME + File.separator + APPLICATION_NAME, FILE_PATH, ARG1, ARG2};
        action = new ExternalProgramExecutionAction(program, arguments);
            
    }
    
    @Test
    public void ExternalProgramExecutionAction(){
        assertNotNull(action);
    }
    

    /**
     * Test of getDescription method, of class ExternalProgramExecutionAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Program Execution Action of:  " + program;
        assertEquals(expectedDescription, action.getDescription());
    }

    /**
     * Test of execute method, of class ExternalProgramExecutionAction.
     */
    @Test
    public void testExecute() throws IOException{
        
       assertNotEquals(ARG1+ARG2,readDataFromFile());
        action.execute();
        assertEquals(0,action.getExitCode());
        assertEquals(ARG1+ARG2,readDataFromFile());
    }
    
    private String readDataFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
   
    @After
    public void cleanUp() {
        sourceFile.delete();
    }

}
