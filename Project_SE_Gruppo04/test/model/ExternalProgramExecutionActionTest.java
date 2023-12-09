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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author gruppo_04
 */

//test for ExternalProgramExecutionAction, it uses as a test app "Test1.jar".
//this Test1.jar reads a file and some strings for input, it writes the strings in a specific file 
public class ExternalProgramExecutionActionTest {
    
    //Dynamic path for the needed file.
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String APPLICATION_NAME = "Test1.jar";
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME+ File.separator + "test.txt";
    
    //arguments that will be used in the test
    final String ARG1 = "prova1";
    final String ARG2 = "test1";
    
    // Test variables
    private ExternalProgramExecutionAction action;
    private String program;
    private String[] arguments;
    private File sourceFile;
    
    // Initializes ExternalProgramExecutionAction parameters for testing
    //create a new file and a new action with java command and for arguments "-jar"  plus the path of the file 
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
     *First, check that ARG1 and ARG2 are not present in the file; then, execute
     *the action and verify that both ARG1 and ARG2 are present and that it has returned 0 
     * as the exit code
     */
    @Test
    public void testExecute() throws IOException{
        
       assertNotEquals(ARG1+ARG2,readDataFromFile());
        action.execute();
        assertEquals(0,action.getExitCode());
        assertEquals(ARG1+ARG2,readDataFromFile());
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        action.addAction(action);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        action.removeAction(action);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        action.getAction();
    }
    
    //method for reading file
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
   
     //delete the file
    @After
    public void cleanUp() {
        sourceFile.delete();
    }

}
