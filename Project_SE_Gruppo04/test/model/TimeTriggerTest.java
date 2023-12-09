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
public class TimeTriggerTest {
    private Trigger timeTrigger;
    private String time;
    
    @Before
    public void setUp(){
        timeTrigger = new TimeTrigger(LocalTime.now());
        time = LocalTime.now().toString();
    }
    
    @Test
    public void testTimeTrigger(){
        assertNotNull(timeTrigger);
    }
   
    @Test
    public void testCheck() {
        TimeTrigger t=new TimeTrigger(LocalTime.now());
        assertEquals(true, t.check());
    }
    
    @Test
    public void testGetDescription(){
        assertEquals(timeTrigger.getDescription()," Trigger Type: Time "+ time);
    }
    
    @Test (expected=UnsupportedOperationException.class)
    public void testAddTrigger(){
        timeTrigger.addTrigger(timeTrigger);
    }
}
