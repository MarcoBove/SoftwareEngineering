/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Andre
 */
public class FileAppendActionTest {
    
    private FileAppendAction fileAppendAction;
    @Before
    public void setup(){
        File txtFile = new File("path_to_txt_file.txt");
        fileAppendAction = new FileAppendAction("append",txtFile);
    }
    
    @Test
    public void testFileAppendAction(){
        FileAppendAction f=null;
        f = new FileAppendAction("append",new File("path_to_txt_file.txt"));
        assertNotNull(f);
    }
    
    @Test
    public void testGetDescription() {
        String expectedDescription = "File Append Action of:  " + "append" + ". " + "\nFile= " + "path_to_txt_file.txt";
        assertEquals(expectedDescription, fileAppendAction.getDescription());
    }
}
