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
public class DayOfWeekTriggerTest {
    
    // Test variable
    private Trigger trigger;
    
    // Initializes DayOfWeekTrigger parameters for testing
    @Before
    public void setUp(){
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek());
    }
    
    @Test
    public void testDayOfWeekTrigger(){
        assertNotNull(trigger);
    }
   
    @Test
    public void testCheck() {
        assertEquals(true, trigger.check());
    }
    
    @Test
    public void testGetDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: Day of Week:  " + LocalDate.now().getDayOfWeek().toString());
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        trigger.addTrigger(trigger);
    }
}
