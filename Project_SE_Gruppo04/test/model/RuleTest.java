/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.Duration;
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
        r=new Rule("expected","TestProva",Duration.ZERO);
    }
    @Test
    public void testGetName(){
        assertEquals("expected",r.getName());
    }
    
}
