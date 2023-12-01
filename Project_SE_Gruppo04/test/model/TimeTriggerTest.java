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
 * @author schet
 */
public class TimeTriggerTest {
    private Trigger timeTrigger;
    
    @Before
    public void setUp(){
        timeTrigger = new TimeTrigger(LocalTime.now());
    }
    
    @Test
    public void testTimeTrigger(){
        assertNotNull(timeTrigger);
    }
   
    @Test
    public void testCheck() {
        assertEquals(true, timeTrigger.check());
    }
    
    @Test
    public void getDescription(){
        assertEquals(timeTrigger.getDescription()," Trigger Type: Time "+ LocalTime.now().toString());
    }
    
}
