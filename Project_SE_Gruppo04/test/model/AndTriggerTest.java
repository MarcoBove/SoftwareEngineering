/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gruppo_04
 */
public class AndTriggerTest {
    
    // Test variables
    private ArrayList<Trigger> triggers;
    private Trigger andTrigger;
    private Trigger andTrigger2;
    private Trigger andTrigger3;
    
    // Initializes AndTrigger parameters for testing
    @Before
    public void setUp(){
        triggers = new ArrayList<>();
        triggers.add( new DateTrigger(LocalDate.now()));
        triggers.add(  new DateTrigger(LocalDate.of(2023, Month.MARCH, 3)));
        andTrigger = new AndTrigger(); 
        andTrigger2 = new AndTrigger();
        andTrigger3 = new AndTrigger();
        andTrigger.addTrigger(triggers.get(0));  //true
        andTrigger.addTrigger(triggers.get(1));//false
        andTrigger2.addTrigger(triggers.get(1));//false
        andTrigger2.addTrigger(triggers.get(1));//false
        andTrigger3.addTrigger(triggers.get(0));  //true
        andTrigger3.addTrigger(triggers.get(0));  //true
        
    }
    
    @Test
    public void testAndTrigger(){
        assertNotNull(andTrigger);
    }
   
    @Test
    public void testCheck() {
         //returns true if DateTrigger1 is true or DateTrigger2 is true, false if are both false;
        assertFalse(andTrigger.check());  //true,false
        assertFalse(andTrigger2.check());  //false,false
        assertTrue(andTrigger3.check());  // true,true
    }
    
    @Test
    public void testGetDescription(){
         StringBuilder description = new StringBuilder("(");
        for (int i = 0; i < triggers.size(); i++) {
            description.append(triggers.get(i).getDescription());
             if(i<triggers.size()-1)
                description.append(" AND ");
        }
        description.append(")");
        assertEquals(andTrigger.getDescription(),description.toString());
    }
    
    @Test
    public void testAddTrigger(){
        //andTrigger3 is true,true
        andTrigger3.addTrigger(triggers.get(1)); //add a false trigger
        assertFalse( andTrigger3.check());  //false,true,true
    }
    
    @Test
    public void testGetTriggers(){
        assertEquals(triggers,andTrigger.getTriggers());
    }
}
