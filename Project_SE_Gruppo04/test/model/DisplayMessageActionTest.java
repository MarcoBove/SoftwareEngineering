/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author gruppo_04
 */
public class DisplayMessageActionTest {
    
    /**
     * Test of execute method, of class DisplayMessageAction.
     */
    
    // Test variable
    private DisplayMessageAction displayMessageAction;

    // Initializes DisplayMessageAction parameters for testing
    @Before
    public void setup(){
        displayMessageAction = new DisplayMessageAction("Test message");
    }
    
    @Test
    public void testExecute() {
        assertTrue(displayMessageAction.getDescription().contains("Test message"));
    }
    /**
     * Test of getDescription method, of class DisplayMessageAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Display Message Action of: " + "Test message";
        assertEquals(expectedDescription, displayMessageAction.getDescription());
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testLog(){
        displayMessageAction.log("");
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testAddAction(){
        displayMessageAction.addAction(displayMessageAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testRemoveAction(){
        displayMessageAction.removeAction(displayMessageAction);
    }
    
    @Test (expected = UnsupportedOperationException.class)
    public void testGetAction(){
        displayMessageAction.getAction();
    }
    
}

