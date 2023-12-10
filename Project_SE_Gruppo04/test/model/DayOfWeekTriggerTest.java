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
 * @author Andre
 */
public class DayOfWeekTriggerTest {
    
    private Trigger trigger;
    
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
    public void getDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: Day of Week:  " + LocalDate.now().getDayOfWeek().toString());
    }
}
