/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class NotTriggerTest {
    
    // Test variables
    private Trigger trigger;
    private Trigger notTrigger;
    
    //Creates a new Trigger for testing
    @Before
    public void setUp(){
        trigger = new DateTrigger(LocalDate.now());
        notTrigger = new NotTrigger(trigger);
    }
    
    @Test
    public void testNotTrigger(){
        assertNotNull(notTrigger);
    }
   
    @Test
    public void testCheck() {
        assertEquals(false, notTrigger.check()); //returns false if DateTrigger is true;
    }
    
    @Test
    public void testGetDescription(){
        assertEquals(notTrigger.getDescription()," (NOT " + trigger.getDescription() + ")" );
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        notTrigger.addTrigger(trigger);
    }
}
