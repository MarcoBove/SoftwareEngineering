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
 * @author Andre
 */
public class FilePresenceTest {
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String SAMPLE_DIRECTORY = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME;
    private Trigger trigger;
    private File directory;
    private String fileName;
    
    @Before
    public void setUp() throws IOException{
        directory = new File(SAMPLE_DIRECTORY);
        fileName = "testFilePresence.txt";
        trigger = new FilePresenceTrigger(fileName,directory);
    }
    
    @Test
    public void testFileSizeTrigger(){
        assertNotNull(trigger);
    }
   
    @Test
    public void testCheck() throws IOException {
        assertEquals(false, trigger.check());
        File file = new File(directory, fileName);   //creating the file in the directory
        file.createNewFile();
        assertEquals(true, trigger.check());
    }
    
    @Test
    public void getDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: File Presence Trigger:  File: " + fileName  + ", Directory: "+ directory.getName());
    }
    
    @After
    public void destroyFile(){
        File file= new File(SAMPLE_DIRECTORY + File.separator+ fileName);
        file.delete();     
    }
    
    }
    


