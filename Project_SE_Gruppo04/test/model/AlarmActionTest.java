/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39327
 */
public class AlarmActionTest {
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
    
    /*@Test
    public void testExecute() {
        String relativePath = "\\test\\sample\\file_example_WAV_1MG.wav";
        File audioFile = new File(System.getProperty("user.dir") + relativePath);
        System.out.println(audioFile.getAbsolutePath());
        assertTrue(audioFile.exists());
        
    }*/

    /**
     * Test of getDescription method, of class AlarmAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Alarm Action: " + "\nFile= " + "path_to_audio_file.wav";
        assertEquals(expectedDescription, alarmAction.getDescription());
    }
    
}
