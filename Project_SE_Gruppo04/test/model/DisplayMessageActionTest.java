/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import javafx.scene.control.Alert;
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
public class DisplayMessageActionTest {
    
    /**
     * Test of execute method, of class DisplayMessageAction.
     */
    @Test
    public void testExecute() {
        String testMessage = "Test message";
        DisplayMessageAction displayMessageAction = new DisplayMessageAction(testMessage);
 
    }

    /**
     * Test of getDescription method, of class DisplayMessageAction.
     */
    @Test
    public void testGetDescription() {
        String testMessage = "Test message";
        DisplayMessageAction displayMessageAction = new DisplayMessageAction(testMessage);
        String expectedDescription = "Display Message Action of: " + testMessage;
        String actualDescription = displayMessageAction.getDescription();
        assertEquals(expectedDescription, actualDescription);

    }
    
}

