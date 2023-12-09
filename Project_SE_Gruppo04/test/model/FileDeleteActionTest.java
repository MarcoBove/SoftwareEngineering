/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gruppo_04
 */
public class FileDeleteActionTest {
    
    private FileDeleteAction delete;
    private File file;
    
    
    
    @Before
    public void setup() throws IOException{
        file = new File("testFile.dat");
        delete = new FileDeleteAction(file);
    }
   /**
     * Test of getDescription method, of class FileDeleteAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "File Delete Action of:  " + "\nFile= " + delete.getFile().getName();
        assertEquals(expectedDescription, delete.getDescription());
    }

    /**
     * Test of execute method, of class FileDeleteAction.
     */
    @Test
    public void testExecute() {
        delete.execute();
        assertFalse(file.exists());
    }
    
    @Test
    public void testFileDeleteAction(){
        FileDeleteAction f=null;
        f = new FileDeleteAction(new File("path_to_txt_file.txt"));
        assertNotNull(f);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        delete.addAction(delete);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        delete.removeAction(delete);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        delete.getAction();
    }
}
