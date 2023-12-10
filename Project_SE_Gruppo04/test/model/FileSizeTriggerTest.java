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
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class FileSizeTriggerTest {
    
    // Test variables
    private Trigger trigger;
    private File file;
    private int max;
    private String unit;
    
    // Initializes FileSizeTrigger parameters for testing
    @Before
    public void setUp() throws IOException{
        file = new File("testFile.txt");
        max=10;
        unit="B";
        trigger = new FileSizeTrigger(file,unit,max);
    }
    
    // Validates the creation of a FileSizeTrigger instance
    @Test
    public void testFileSizeTrigger(){
        assertNotNull(trigger);
    }
   
    // Tests the FileSizeTrigger logic by checking file size conditions
    @Test
    public void testCheck() throws IOException {
        assertEquals(false, trigger.check());
        writeToFile(file,"This string is a larger content than 10 byte.");
        assertEquals(true, trigger.check());
    }
    
    // Checks if the description matches the expected format
    @Test
    public void testGetDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: File Size Trigger:  File: " + file.getName() + ", Max " + unit + ": " + max);
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        // Tests the unsupported operation exception when attempting to add a trigger
        trigger.addTrigger(trigger);
    }
    
    // method to write content to a file
    private void writeToFile(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testGetTriggers(){
        trigger.getTriggers();
    }
    
    //Deletes the test file created after testing
    @After
    public void clean(){
        file.delete();
    }
    
}
