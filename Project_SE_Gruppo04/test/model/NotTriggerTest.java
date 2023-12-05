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
public class NotTriggerTest {
    private Trigger trigger;
    private Trigger notTrigger;
    
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
    public void getDescription(){
        assertEquals(notTrigger.getDescription()," (NOT " + trigger.getDescription() + ")" );
    }
}
