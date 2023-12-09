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
    
    // Test variables
    private RulesManager rules;
    private Rule r;

    // set up for the execution of the test
    @Before
    public void setup() {
        rules = RulesManager.getInstance();
        r = new Rule("expected_name", "expected_description", Duration.ZERO);
    }
    
    //Verifies if the getInstance() method of the RulesManager class returns the same instance
    //when called multiple times, confirming that the singleton design is implemented correctly.
    @Test 
    public void testGetInstance(){
        RulesManager instance1 = RulesManager.getInstance();
        assertNotNull(instance1);
        RulesManager instance2 = RulesManager.getInstance();
        assertSame(instance1, instance2);
    }
    
    //Tests the retrieval of a specific rule by adding it to the rules collection,
    //then checking if the rule retrieved matches the expected rule.
    @Test
    public void testGetRule() {
        rules.addRule(r);
        assertEquals(r, rules.getRule(r));
        rules.removeRule(r);
    }

    
    /**
    * Tests the retrieval of all rules in the rules collection,
    * adding a rule, and verifying if the retrieved rules contain the expected rule.
    */
   @Test
    public void testGetRules() {
        rules.addRule(r);
        assertTrue(rules.getRules().contains(r));
        rules.removeRule(r);
    }

    
    /**
    * Tests the addition of a rule to the rules collection, verifying that initially 
    * there's no rule with the given key, then adding the rule and ensuring it is present.
    */

    @Test
    public void testAddRule() {
        assertNull(rules.getRule(r));
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeRule(r);
    }
    
    /**
    * Tests the retrieval of the last added rule in the rules collection,
    * adding a rule, and ensuring the getLast() method retrieves the expected rule.
    */
    @Test
    public void testGetLast(){
        rules.addRule(r);
        assertEquals(r, rules.getLast());
        rules.removeRule(r);
    }
    
    /**
    * Tests the removal of a specific rule from the rules collection,
    * adding the rule, checking its existence, removing it, and verifying its absence.
    */
    @Test
    public void testRemoveRule(){
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeRule(r);
        assertNull(rules.getRule(r));        
    }
    
    /**
    * Tests the removal of the last added rule from the rules collection,
    * adding a rule, removing the last added rule, and verifying its absence.
    */
    @Test
    public void testRemoveLast(){
        rules.addRule(r);
        assertEquals(r,rules.getRule(r));
        rules.removeLast();
        assertNull(rules.getRule(r));  
    }
    
    /**
    * Tests the saving of rules to a file, adding a rule, saving it to a file,
    * checking the file existence, deleting the file, and removing the rule.
    */
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
    
    
    /**
    * Tests the uploading of rules from a file, saving a rule to a file,
    * removing it, uploading rules from the file, checking its existence,
    * deleting the test file, and removing the rule.
    */
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
    
    /**
    * Tests the retrieval of actions associated with rules,
    * setting an action to a rule, adding the rule, and verifying
    * if the actions collection contains the associated action.
    */
    @Test
    public void testGetActions(){
        Action a = new DisplayMessageAction("test");
        r.setAction(a);
        rules.addRule(r);
        assertTrue(rules.getActions().contains(a));
        rules.removeRule(r);
    }
}