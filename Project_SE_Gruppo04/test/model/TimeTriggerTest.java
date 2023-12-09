/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gruppo_04
 */

// Test for Time Trigger class
public class TimeTriggerTest {
    private Trigger timeTrigger;
    private String time;
    
    //Set up for test class, it creates a new Trigger with the time now
    @Before
    public void setUp(){
        timeTrigger = new TimeTrigger(LocalTime.now());
        time = LocalTime.now().toString();
    }
    
     //It verifies if the time trigger is not null
    @Test
    public void testTimeTrigger(){
        assertNotNull(timeTrigger);
    }
   
    //It verifies the condition check when a time trigger is created 
    @Test
    public void testCheck() {
        TimeTrigger t=new TimeTrigger(LocalTime.now());
        assertEquals(true, t.check());
    }
    
    //It verifies the description string
    @Test
    public void testGetDescription(){
        assertEquals(timeTrigger.getDescription()," Trigger Type: Time "+ time);
    }
    
    //Test the case where adding a trigger to itself raises an UnsupportedOperation excepti
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        timeTrigger.addTrigger(timeTrigger);
    }
}
