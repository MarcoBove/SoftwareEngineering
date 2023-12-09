/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */

//test for ExternalProgramExecutionAction, it uses as a test app "Test2.jar".
//this Test2.jar reads a file and some strings for input, it returns 0 if the file contains
//one of the input string
//otherwise 1
public class ExternalProgramTriggerTest {
    
    //Dynamic path for the needed file
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String APPLICATION_NAME = "Test2.jar";
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME+ File.separator + "test2.txt";
    
    //input string
    final String ARG1 = "string1";
    
    // Test variables
    private ExternalProgramTrigger trigger;
    private ExternalProgramTrigger trigger2;
    private String program;
    private String[] arguments;
    private File sourceFile;
    private final int EXIT_CODE = 0;
    
    // Initializes ExternalProgramTrigger parameters for testing
    @Before
    public void setup() throws IOException{
        sourceFile = new File(FILE_PATH);
        if(!sourceFile.exists())
            sourceFile.createNewFile();
        //the program returns 0 if it contains the arguments in the file, 1 otherwise
        program = "java"; 
        arguments = new String[]{"-jar",LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME + File.separator + APPLICATION_NAME, FILE_PATH,ARG1};
        trigger = new ExternalProgramTrigger(EXIT_CODE,program, arguments);
    }
    
    @Test //constructor
    public void ExternalProgramExecutionAction(){
        assertNotNull(trigger);
    }

    @Test
    public void testGetDescription() {
        String expectedDescription = " Trigger Type: External Program: "+ program + "Exit Value: "+ EXIT_CODE; 
        assertEquals(expectedDescription, trigger.getDescription());
    }

    @Test
    public void testExecute() throws IOException{
        assertFalse(trigger.check());     //the file does not contain arg1
        writeArg1(FILE_PATH,ARG1);
        assertTrue(trigger.check());        //the file does contain arg1
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        trigger.addTrigger(trigger);
    }
    
    private void writeArg1(String file_path,String arg){
        try (FileWriter writer = new FileWriter(file_path)) {
            //write arg in the file
            writer.write(arg);
            System.out.println("Stringa scritta con successo sul file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void cleanUp() {
        sourceFile.delete();
    }
}
