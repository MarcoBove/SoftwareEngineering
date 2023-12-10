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
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gruppo_04
 */
public class FileCopyActionTest {
    
    //Dynamic path for the file we need.
    final static String SAMPLE_FOLDER_NAME = "samples";
    final static String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final static String DIRECTORY_PATH = LOCAL_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME;
    
    // Test variables
    private static File sourceFile;
    private static File destinationDir;
    private static FileCopyAction copyAction;
    
    // Initializes FileCopyAction parameters for testing
    @BeforeClass
    public static void setUpClass() throws IOException {
        sourceFile = new File("sourceCopyFile.txt");
        if(!sourceFile.exists())
            sourceFile.createNewFile();
        destinationDir = new File(DIRECTORY_PATH);
        copyAction = new FileCopyAction(sourceFile, destinationDir);
    }

    /**
     * Test of getDescription method, of class FileCopyAction.
     */
    @Test
    public void testGetDescription() {
        FileCopyAction copyAction1 = new FileCopyAction(sourceFile, destinationDir);

        String expectedDescription = "File Copy Action of: " + "File= " + sourceFile.getName() + "       copied into:        " + destinationDir;
        assertEquals(expectedDescription, copyAction1.getDescription());
    
    }

    /**
     * Test of execute method, of class FileCopyAction.
     */
    @Test
    public void testExecute() {
        String file[]= sourceFile.getName().split(("\\."));
        String file1=file[0]+"(1)."+file[1];
        Path filePath = Paths.get(destinationDir.getAbsolutePath(), file1);
        destinationDir=filePath.toFile();
        assertFalse(Files.exists(destinationDir.toPath()));
        
        copyAction.execute();
        
        assertTrue(Files.exists(destinationDir.toPath()));
        destinationDir.delete();
        sourceFile.delete();
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        copyAction.addAction(copyAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        copyAction.removeAction(copyAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        copyAction.getAction();
    }

}
