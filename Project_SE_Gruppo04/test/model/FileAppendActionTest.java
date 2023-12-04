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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Andre
 */
public class FileAppendActionTest {
    
    private FileAppendAction fileAppendAction;
    private File testFile;
    
    @Before
    public void setup() throws IOException{
        testFile = File.createTempFile("testFile", ".txt");
        fileAppendAction = new FileAppendAction("Appended text",testFile);
    }
    
    @Test
    public void testFileAppendAction() throws IOException{
        FileAppendAction f=null;
        f = new FileAppendAction("Appended text",File.createTempFile("testFile2", ".txt"));
        assertNotNull(f);
    }
    
    @Test
    public void testGetFile(){
        assertEquals(testFile,fileAppendAction.getFile());
    }
    
    @Test
    public void testSetFile() throws IOException{
        File testFile2 = File.createTempFile("testFile2", ".txt");
        fileAppendAction.setFile(testFile2);
        assertEquals(testFile2,fileAppendAction.getFile());
    }
    
    @Test
    public void testGetDescription() {
        String expectedDescription = "File Append Action of: " + "Appended text" + ". " + "\nFile= " + fileAppendAction.getFile().getName();
        assertEquals(expectedDescription, fileAppendAction.getDescription());
    }
    
    @Test
    public void testExecute() throws IOException{
        fileAppendAction.execute();
        String actualContent = new String(Files.readAllBytes(fileAppendAction.getFile().toPath()));
        assertEquals(actualContent,"Appended text" + System.lineSeparator());
    }
    
    @Test
    public void testLog() throws IOException{
        File logTestFile = File.createTempFile("logTestFile", ".txt");
        fileAppendAction.log(logTestFile.getAbsolutePath());
        
        
        // Ottieni l'ora attuale
            LocalTime currentTime = LocalTime.now();

            // Formatta l'ora come una stringa
            DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = currentTime.format(formatterHour);
            
        // Ottieni la data corrente
            LocalDate currentDate = LocalDate.now();

            // Formatta la data come una stringa
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = currentDate.format(formatterData);
            
            
            
        String message = "Today " + dateString+ " at " + timeString+ " The \"File Append Action\" has been executed successfully." ;
        String actualContent = new String(Files.readAllBytes(logTestFile.toPath()));
       
        assertEquals(actualContent,message + System.lineSeparator());
    }
}
