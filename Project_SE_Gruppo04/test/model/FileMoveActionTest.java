/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class FileMoveActionTest {
    
    //Dynamic path for the needed file 
    final static String SAMPLE_FOLDER_NAME = "samples";
    final static String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final static String DIRECTORY_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME;
    
    // Test variables
    private static File sourceFile;
    private static File destinationDir;
    private static FileMoveAction moveAction;
    
    // Initializes FileMoveAction parameters for testing
    @BeforeClass
    public static void setUpClass() throws IOException{
        sourceFile = new File("sourceMoveFile.txt");
        if(!sourceFile.exists())
            sourceFile.createNewFile();
        destinationDir = new File(DIRECTORY_PATH);
        moveAction = new FileMoveAction(sourceFile, destinationDir);
        }


    /**
     * Test of getDescription method, of class FileMoveAction.
     */
    @Test
    public void testGetDescription() {
        FileMoveAction moveAction = new FileMoveAction(sourceFile, destinationDir);

        String expectedDescription = "File Move Action of: " + "File= " + sourceFile.getName() +  "       moved into:        " + destinationDir;
        assertEquals(expectedDescription, moveAction.getDescription());

    }

    /**
     * Test of execute method, of class FileMoveAction.
     */
    @Test
    public void testExecute(){
        String file[]= sourceFile.getName().split(("\\."));
        String file1=file[0]+"(1)."+file[1];
        Path filePath = Paths.get(destinationDir.getAbsolutePath(), file1);
        destinationDir=filePath.toFile();
        
        assertFalse(Files.exists(destinationDir.toPath()));
        
        moveAction.execute();
        
        assertTrue(Files.exists(destinationDir.toPath()));
        assertFalse(sourceFile.exists());
        destinationDir.delete();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        moveAction.addAction(moveAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        moveAction.removeAction(moveAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        moveAction.getAction();
    }
    
}
