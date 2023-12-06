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
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39327
 */
public class FileMoveActionTest {
    
    private static File sourceFile;
    private static File destinationDir;
    
    public FileMoveActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        sourceFile = new File("sourceFile.txt");
        destinationDir = new File("destinationDir");
        if (!destinationDir.exists()) {
            destinationDir.mkdir();
        }
   
    }
    
   
    @After
    public void tearDown() {
        sourceFile.delete();
        destinationDir.delete();
    }

    /**
     * Test of getDescription method, of class FileMoveAction.
     */
    @Test
    public void testGetDescription() {
        FileMoveAction moveAction = new FileMoveAction(sourceFile, destinationDir);

        String expectedDescription = "\nFile= " + sourceFile.getName() + "      moved into:       " + destinationDir;
        assertEquals(expectedDescription, moveAction.getDescription());

    }

    /**
     * Test of execute method, of class FileMoveAction.
     */
    @Test
    public void testExecute(){
        FileMoveAction moveAction = new FileMoveAction(sourceFile, destinationDir);
        
        String file[]= sourceFile.getName().split(("\\."));
        String file1=file[0]+"(1)."+file[1];
        
        Path filePath = Paths.get(destinationDir.getAbsolutePath(), file1);
        
        
        destinationDir=filePath.toFile();
        assertFalse(Files.exists(destinationDir.toPath()));
        
        moveAction.execute();
        
        assertTrue(Files.exists(destinationDir.toPath()));
        assertFalse(sourceFile.exists());
    }
    
}
