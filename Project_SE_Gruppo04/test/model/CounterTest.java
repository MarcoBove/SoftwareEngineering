/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 39327
 */
public class CounterTest {
    
     private static Counter counter;
    
    public CounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        counter = new Counter("TestCounter", 10);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Counter.
     */
    @Test
    public void testGetName() {
       
        String name = counter.getName();
        assertEquals("TestCounter", name);
    }

    /**
     * Test of setName method, of class Counter.
     */
    @Test
    public void testSetName() {
        
       counter.setName("NewName");

        assertEquals("NewName", counter.getName());
    }

    /**
     * Test of getValue method, of class Counter.
     */
    @Test
    public void testGetValue() {
        
        int value = counter.getValue();
        assertEquals(10, value);
    }

    /**
     * Test of setValue method, of class Counter.
     */
    @Test
    public void testSetValue() {
        
        counter.setValue(20);
        assertEquals(20, counter.getValue());
        
    }
    
}
