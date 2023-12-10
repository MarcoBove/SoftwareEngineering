/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import javafx.scene.media.Media;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    @Test
    public void testExecute() {
        String relativePath = "\\test\\sample\\file_example_WAV_1MG.wav";
        File audioFile = new File(System.getProperty("user.dir") + relativePath);
        System.out.println(audioFile.getAbsolutePath());
        assertTrue(audioFile.exists());
        
    }

    /**
     * Test of getDescription method, of class AlarmAction.
     */
    @Test
    public void testGetDescription() {
        File audioFile = new File("path_to_audio_file.wav");
        AlarmAction alarmAction = new AlarmAction(audioFile);
        String expectedDescription = "Alarm Action:  " + audioFile.getName();
        String actualDescription = alarmAction.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }
    
}
