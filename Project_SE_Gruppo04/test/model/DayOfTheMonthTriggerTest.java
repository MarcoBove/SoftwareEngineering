/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gruppo_04
 */
public class DayOfTheMonthTriggerTest {
    
    // Test variables
    private Trigger trigger;
    private  int day;
    
    // Initializes DayOfTheMonthTrigger parameters for testing
    @Before
    public void setUp() {
        day=LocalDate.now().getDayOfMonth();
        trigger = new DayOfTheMonthTrigger(day);
    }

    /**
     * Test of check method, of class DayOfTheMonthTrigger.
     */
    @Test
    public void testCheck() {
        
    }

    /**
     * Test of getDescription method, of class DayOfTheMonthTrigger.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Trigger Type: Day of the Month:   " + day;
        assertEquals(expectedDescription,trigger.getDescription());
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        trigger.addTrigger(trigger);
    }
    
}
