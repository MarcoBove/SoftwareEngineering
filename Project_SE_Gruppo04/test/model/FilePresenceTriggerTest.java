/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class FilePresenceTriggerTest {
    // Constants for sample folder and project path
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String SAMPLE_DIRECTORY = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME;
    
    // Test variables
    private Trigger trigger;
    private File directory;
    private String fileName;
    
    
    // Initializes FilePresenceTrigger parameters for testing
    @Before
    public void setUp() throws IOException{
        directory = new File(SAMPLE_DIRECTORY);
        fileName = "testFilePresence.txt";
        trigger = new FilePresenceTrigger(fileName,directory);
    }
    
    // Validates the creation of a FilePresenceTrigger instance
    @Test
    public void testFileSizeTrigger(){
        assertNotNull(trigger);
    }
   
    @Test
    public void testCheck() throws IOException {
        // Tests the FilePresenceTrigger logic by checking file presence in the directory
        assertEquals(false, trigger.check()); // initially false
        File file = new File(directory, fileName);   // creates the file in the directory
        file.createNewFile();
        assertEquals(true, trigger.check()); // file presence detected
    }
    
    // Checks if the description matches the expected format
    @Test
    public void testGetDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: File Presence Trigger:  File: " + fileName  + ", Directory: "+ directory.getName());
    }
    
    // Tests the unsupported operation exception when attempting to add a trigger
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        trigger.addTrigger(trigger);
    }
    
    // Deletes the test file created after testing
    @After
    public void destroyFile(){
        File file= new File(SAMPLE_DIRECTORY + File.separator+ fileName);
        file.delete();     
    }
    
    }
    


