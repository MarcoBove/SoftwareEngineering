/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.LocalDate;
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
public class DayOfTheMonthTriggerTest {
    
    private Trigger trigger;
    private  int day;
    
    public DayOfTheMonthTriggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        day=LocalDate.now().getDayOfMonth();
        trigger = new DayOfTheMonthTrigger(day);
    }
    
    @After
    public void tearDown() {
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
    
}