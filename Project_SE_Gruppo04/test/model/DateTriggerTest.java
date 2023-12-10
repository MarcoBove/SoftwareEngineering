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
public class DateTriggerTest {
     private Trigger trigger;
    
    @Before
    public void setUp(){
        trigger = new DateTrigger(LocalDate.now());
    }
    
    @Test
    public void testDateTrigger(){
        assertNotNull(trigger);
    }
   
    @Test
    public void testCheck() {
        assertEquals(true, trigger.check());
    }
    
    @Test
    public void getDescription(){
        assertEquals(trigger.getDescription()," Trigger Type: Date :  " + LocalDate.now());
    }
}

