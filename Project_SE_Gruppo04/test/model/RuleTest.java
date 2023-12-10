/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre
 */

public class RuleTest {
    
    private Rule r;
    @Before
    public void setup(){
        r=new Rule("expected_name","expected_description",Duration.ZERO);
    }
    
    @Test //costructor
    public void testRule(){
        assertEquals("expected_name",r.getName());
        assertEquals("expected_description",r.getDescription());
        assertEquals(Duration.ZERO,r.getSleepingPeriod());
        assertEquals(true,r.isEnable());
        assertNull(r.getAction());
        assertNull(r.getTrigger());
        assertNull(r.getLastFired());
    }
    
    @Test
    public void testGetName(){
        assertEquals("expected_name",r.getName());
    }
    
    @Test
    public void testSetName(){
        r.setName("testSetName");
        assertEquals("testSetName",r.getName());
    }
    
    @Test
    public void testGetDescription(){
        assertEquals("expected_description",r.getDescription());
    }
    
    @Test
    public void testSetDescription(){
        r.setDescription("testSetDescription");
        assertEquals("testSetDescription",r.getDescription());
    }
    
    @Test
    public void testIsEnable() {
        assertEquals(true,r.isEnable());
    }

    @Test
    public void setEnable() {
        r.setEnable(false);
        assertEquals(false,r.isEnable());
    }

    @Test
    public void testGetSleepingPeriod() {
        assertEquals(Duration.ZERO,r.getSleepingPeriod());
    }

    @Test
    public void setSleepingPeriod() {
        r.setSleepingPeriod(Duration.ofDays(1).plusHours(2).plusMinutes(30));
        assertEquals(Duration.ofDays(1).plusHours(2).plusMinutes(30),r.getSleepingPeriod());
    }

    @Test
    public void testGetAction() {
        assertNull(r.getAction());
    }

    @Test
    public void testSetAction() {
        r.setAction(new DisplayMessageAction("Prova"));
        assertNotNull(r.getAction());
    }


    @Test
    public void testGetTrigger() {
        assertNull(r.getTrigger());
    }

    @Test
    public void testSetTrigger() {
        r.setTrigger(new TimeTrigger(LocalTime.of(0,0)));
        assertNotNull(r.getTrigger());
    }
    
    @Test
    public void testToString(){
        assertEquals("expected_name",r.toString());
    }
    
    @Test
    public void testEquals(){
        assertEquals(true,r.equals(new Rule("expected_name","",Duration.ZERO)));
    }
    
    @Test
    public void testGetLastFired(){
        assertNull(r.getLastFired());
    }
    
    @Test
    public void testSetLastFired(){
        r.setLastFired(LocalDateTime.now());
        assertEquals(r.getLastFired(),LocalDateTime.now());
    }
    
    @Test
    public void testHasTimePassed(){
        assertEquals(false,r.hasTimePassed(LocalDateTime.now())); //DURATION=ZERO -> return false
        r.setLastFired(LocalDateTime.of(2023, 11, 27, 12, 0, 0));
        r.setSleepingPeriod(Duration.ofHours(1));
        assertEquals(false,r.hasTimePassed(LocalDateTime.of(2023, 11, 27, 12, 59, 59)));
        assertEquals(true,r.hasTimePassed(LocalDateTime.of(2023, 11, 27, 13, 0, 1))); 
    }
    
    
}
