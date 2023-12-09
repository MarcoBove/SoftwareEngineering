/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.time.Duration;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class RulesManagerTest {

    private RulesManager rules;
    private Rule r;

    @Before
    public void setup() {
        rules = RulesManager.getInstance();
        r = new Rule("expected_name", "expected_description", Duration.ZERO);
    }
    
    @Test 
    public void testGetInstance(){
        RulesManager instance1 = RulesManager.getInstance();
        assertNotNull(instance1);
        RulesManager instance2 = RulesManager.getInstance();
        assertSame(instance1, instance2);
    }
    
    @Test
    public void testGetRule() {
        rules.addRule(r);
        assertEquals(r, rules.getRule(r));
        rules.removeRule(r);
    }

   @Test
    public void testGetRules() {
        rules.addRule(r);
        assertTrue(rules.getRules().contains(r));
        rules.removeRule(r);
    }

    @Test
    public void testAddRule() {
        assertNull(rules.getRule(r));
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeRule(r);
    }
    
    @Test
    public void testGetLast(){
        rules.addRule(r);
        assertEquals(r, rules.getLast());
        rules.removeRule(r);
    }
    
    @Test
    public void testRemoveRule(){
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeRule(r);
        assertNull(rules.getRule(r));        
    }
    
    @Test
    public void testRemoveLast(){
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeLast();
        assertNull(rules.getRule(r));  
    }
    
    @Test
    public void testSaveRules(){
        rules.addRule(r);
        File testFile = new File("t.dat");
        assertFalse(testFile.exists());
        rules.saveRules(testFile);
        assertTrue(testFile.exists());
        testFile.delete();
        rules.removeRule(r);
    }
    
     @Test
    public void testUploadRules(){
        File testFile = new File("test.dat");
        assertFalse(testFile.exists());
        rules.addRule(r);
        rules.saveRules(testFile);
        rules.removeRule(r);
        rules.uploadRules(testFile);
        assertNotNull(rules.getRule(r));
        assertTrue(testFile.exists());
        testFile.delete();
        rules.removeRule(r);
    }
    
    @Test
    public void testGetActions(){
        Action a = new DisplayMessageAction("test");
        r.setAction(a);
        rules.addRule(r);
        assertTrue(rules.getActions().contains(a));
        rules.removeRule(r);
    }
}