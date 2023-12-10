/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39327
 */
public class AlarmActionTest {
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String FILE_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME+ File.separator + "file_example_WAV_1MG.wav";
    /**
     * Test of execute method, of class AlarmAction.
     */
    private AlarmAction alarmAction;
    @Before
    public void setup(){
        File audioFile = new File("path_to_audio_file.wav");
        alarmAction = new AlarmAction(audioFile);
    }
    
    @Test
    public void testAlarmAction(){
        AlarmAction a=null;
        a = new AlarmAction(new File("path_to_audio_file.wav"));
        assertNotNull(a);
    }
    
    @Test
    public void testExecute() {
        File audioFile = new File(FILE_PATH);
        assertTrue(audioFile.exists());
    }
    

    /**
     * Test of getDescription method, of class AlarmAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Alarm Action: " + "\nFile= " + "path_to_audio_file.wav";
        assertEquals(expectedDescription, alarmAction.getDescription());
    }
    
}
