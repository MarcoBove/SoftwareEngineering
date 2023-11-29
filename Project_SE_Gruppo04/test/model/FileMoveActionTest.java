/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
public class FileMoveActionTest {
    
    private static File sourceFile;
    private static File destinationDir;
    
    public FileMoveActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        sourceFile = File.createTempFile("sourceFile",".txt");
        destinationDir = Files.createTempDirectory("destinationDir").toFile();
   
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
    public void testExecute() throws IOException {
        FileMoveAction moveAction = new FileMoveAction(sourceFile, destinationDir);
        moveAction.execute();
        File movedFile = new File(destinationDir, sourceFile.getName());
        assertTrue(movedFile.exists());
    }
    
}