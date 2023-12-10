/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.Duration;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
   
    public void testToString(){
        assertEquals("expected_name",r.toString());
    }
    
    public void testEquals(){
        assertEquals(true,r.equals(new Rule("expected_name","",Duration.ZERO)));
    }
    
    
}
