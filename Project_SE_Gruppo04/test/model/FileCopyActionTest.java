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
public class FileCopyActionTest {
    private static File sourceFile;
    private static File destinationDir;
    
    public FileCopyActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        sourceFile = File.createTempFile("sourceFile",".txt");
        destinationDir = Files.createTempDirectory("destinationDir").toFile();

    }
    
    @After
    public void tearDown() {
        //Cleaning up temporary files after each test
        sourceFile.delete();
        destinationDir.delete();
    }

    /**
     * Test of getDescription method, of class FileCopyAction.
     */
    @Test
    public void testGetDescription() {
        FileCopyAction copyAction = new FileCopyAction(sourceFile, destinationDir);

        String expectedDescription = "\nFile= " + sourceFile.getName() + "       copied into:        " + destinationDir;
        assertEquals(expectedDescription, copyAction.getDescription());
    
    }

    /**
     * Test of execute method, of class FileCopyAction.
     */
    @Test
    public void testExecute() {
        //execute the copy action
        FileCopyAction copyAction = new FileCopyAction(sourceFile, destinationDir);
        copyAction.execute();

        //Verify if the file has benn copied correctly" 
        File copiedFile = new File(destinationDir, sourceFile.getName());
        assertTrue(copiedFile.exists());
    }
    
}