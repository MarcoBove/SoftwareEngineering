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
public class OrTriggerTest {
     private ArrayList<Trigger> triggers;
    private Trigger orTrigger;
    private Trigger orTrigger2;
    private Trigger orTrigger3;
    
    @Before
    public void setUp(){
        triggers = new ArrayList<>();
        triggers.add( new DateTrigger(LocalDate.now()));
        triggers.add(  new DateTrigger(LocalDate.of(2023, Month.MARCH, 3)));
        orTrigger = new OrTrigger(); 
        orTrigger2 = new OrTrigger();
        orTrigger3 = new OrTrigger();
        orTrigger.addTrigger(triggers.get(0));  //true
        orTrigger.addTrigger(triggers.get(1));//false
        orTrigger2.addTrigger(triggers.get(1));//false
        orTrigger2.addTrigger(triggers.get(1));//false
        orTrigger3.addTrigger(triggers.get(0));  //true
        orTrigger3.addTrigger(triggers.get(0));  //true
        
    }
    
    @Test
    public void testOrTrigger(){
        assertNotNull(orTrigger);
    }
   
    @Test
    public void testCheck() {
         //returns true if DateTrigger1 is true or DateTrigger2 is true, false if are both false;
        assertTrue( orTrigger.check());  //true,false
        assertFalse( orTrigger2.check());  //false,false
        assertTrue( orTrigger3.check());  // true,true
    }
    
    @Test
    public void getDescription(){
         StringBuilder description = new StringBuilder("(");
        for (int i = 0; i < triggers.size(); i++) {
            description.append(triggers.get(i).getDescription());
            if(i<triggers.size()-1)
                description.append(" OR ");
        }
        description.append(")");
        assertEquals(orTrigger.getDescription(),description.toString());
    }
    
    @Test
    public void testAddTrigger(){
        //orTrigger2 is false,false
        orTrigger2.addTrigger(triggers.get(0)); //add a true trigger
        assertTrue( orTrigger2.check());  //false,false,true
    }
}

