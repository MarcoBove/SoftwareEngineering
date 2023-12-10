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
        Path filePath = Paths.get(destinationDir.getAbsolutePath(), sourceFile.getName());
        //Verify if the file has benn copied correctly
        
        assertTrue(Files.exists(filePath));
    }
    
}
