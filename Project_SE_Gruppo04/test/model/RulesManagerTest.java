/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.time.Duration;
import java.util.LinkedList;
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
        rules = new RulesManager();
        r = new Rule("expected_name", "expected_description", Duration.ZERO);
        rules.addRule(r);
    }

    @Test
    public void testGetRule() {
        assertEquals(r, rules.getRule(r));
    }

    @Test
    public void testGetRules() {
        LinkedList<Rule> list = new LinkedList<Rule>();
        list.add(r);

        assertEquals(list, rules.getRules());
    }

    @Test
    public void testAddRule() {

        Rule rule = new Rule("expected_name", "expected_description", Duration.ZERO);

        boolean result = rules.addRule(rule);
        assertTrue(result);

    }
    
    @Test
    public void testGetLast(){
        assertEquals(r, rules.getLast());
    }
    
    @Test
    public void testRemoveRule(){
         Rule rule = new Rule("expected_name", "expected_description", Duration.ZERO);

        boolean result = rules.removeRule(rule);
        assertTrue(result);
        
    }
    
    @Test
    public void testRemoveLast(){
        

        Rule rule1 = new Rule("expected_name1", "expected_description1", Duration.ZERO);
        

        rules.addRule(rule1);
       

        
        rules.removeLast();
        
        assertEquals(1, rules.getRules().size());
        
    }
    
    @Test 
    public void testGetInstance(){
        RulesManager instance1 = RulesManager.getInstance();
        assertNotNull(instance1);
        RulesManager instance2 = RulesManager.getInstance();
        assertSame(instance1, instance2);
    }
    
    //checkRules method is not tested because it is a loop of a method already tested 
    
    @Test
    public void testGetFile(){
        
        File testFile = new File("test.dat");
        rules.setFile(testFile);
        
        assertEquals(testFile,rules.getFile());
        testFile.delete();
    }
    
    @Test
    public void testSetFile(){
        
        File testFile = new File("test.dat");
        rules.setFile(testFile);
        
        assertEquals(testFile,rules.getFile());
        testFile.delete();
    }
    
    
    @Test
    public void testSaveRules(){
        File testFile = new File("test.dat");
        rules.setFile(testFile);
        rules.saveRules();
        assertTrue(testFile.exists());
        testFile.delete();
    }
    
     @Test
    public void testUploadRules(){
        File testFile = new File("test.dat");
        rules.setFile(testFile);
        rules.saveRules();
        rules.uploadRules();
        assertTrue(testFile.exists());
        testFile.delete();
    }
}