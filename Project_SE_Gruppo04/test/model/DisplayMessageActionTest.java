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
 * @author 39327
 */
public class DisplayMessageActionTest {
    
    /**
     * Test of execute method, of class DisplayMessageAction.
     */
    private DisplayMessageAction displayMessageAction;

    @Before
    public void setup(){
        displayMessageAction = new DisplayMessageAction("Test message");
    }
    
    /*@Test
    public void testExecute() {
        String testMessage = "Test message";
        DisplayMessageAction displayMessageAction = new DisplayMessageAction(testMessage);
 
    }*/

    /**
     * Test of getDescription method, of class DisplayMessageAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Display Message Action of: " + "Test message";
        assertEquals(expectedDescription, displayMessageAction.getDescription());
    }
    
}

