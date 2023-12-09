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
    
    private Trigger trigger;
    private File file;
    private int max;
    private String unit;
    
    @Before
    public void setUp() throws IOException{
        file = new File("testFile.txt");
        max=10;
        unit="B";
        trigger = new FileSizeTrigger(file,unit,max);
    }
    
    @Test
    public void testFileSizeTrigger(){
        assertNotNull(trigger);
    }
   
    @Test
    public void testCheck() throws IOException {
        assertEquals(false, trigger.check());
        writeToFile(file,"This string is a larger content than 10 byte.");
        assertEquals(true, trigger.check());
    }
    
    @Test
    public void getDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: File Size Trigger:  File: " + file.getName() + ", Max " + unit + ": " + max);
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        trigger.addTrigger(trigger);
    }
    
    private void writeToFile(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
    
    @After
    public void clean(){
        file.delete();
    }
    
}
