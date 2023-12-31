/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class FileAppendActionTest {
    
    // Test variables
    private FileAppendAction fileAppendAction;
    private File testFile;
    
    // Initializes FileAppendActionTest parameters for testing
    @Before
    public void setup() throws IOException{
        testFile = new File("testFile.txt");
        testFile.createNewFile();
        fileAppendAction = new FileAppendAction("Appended text",testFile);
    }
    
    @After 
    public void cleanUp(){
        testFile.delete();    
    }
    
    
    @Test
    public void testFileAppendAction() throws IOException{
        File tmp = new File("testFile2.txt");
        FileAppendAction f = new FileAppendAction("Appended text", new File("testFile2.txt"));
        assertNotNull(f);
        tmp.delete();
        
    }
    
    @Test
    public void testGetFile(){
        assertEquals(testFile,fileAppendAction.getFile());
    }
    
    @Test
    public void testSetFile() throws IOException{
        File testFile2 = new File("testFile2.txt");
        fileAppendAction.setFile(testFile2);
        assertEquals(testFile2,fileAppendAction.getFile());
        testFile2.delete();
    }
    
    @Test
    public void testGetDescription() {
        String expectedDescription = "File Append Action of: " + "Appended text" + ". " + "File= " + fileAppendAction.getFile().getName();
        assertEquals(expectedDescription, fileAppendAction.getDescription());
    }
    
    @Test
    public void testExecute() throws IOException{
        String actualContent = new String(Files.readAllBytes(fileAppendAction.getFile().toPath()));
        assertNotEquals(actualContent,"Appended text" + System.lineSeparator());
        fileAppendAction.execute();
        actualContent = new String(Files.readAllBytes(fileAppendAction.getFile().toPath()));
        assertEquals(actualContent,"Appended text" + System.lineSeparator());
    }
    
    @Test
    public void testLog() throws IOException{
        File logTestFile = new File("logTestFile.txt");
        fileAppendAction.log(logTestFile.getAbsolutePath());
        
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = currentTime.format(formatterHour);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatterData);   
        String message = "Today " + dateString+ " at " + timeString+ " The \"File Append Action\" has been executed successfully." ;
        String actualContent = new String(Files.readAllBytes(logTestFile.toPath()));
       
        assertEquals(actualContent,message + System.lineSeparator());
        logTestFile.delete();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        fileAppendAction.addAction(fileAppendAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        fileAppendAction.removeAction(fileAppendAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        fileAppendAction.getAction();
    }
}
