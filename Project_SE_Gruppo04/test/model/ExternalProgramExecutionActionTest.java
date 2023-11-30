/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author schet
 */
public class ExternalProgramExecutionActionTest {
    
    private ExternalProgramExecutionAction action;
    private String program;
    private String[] arguments;
    
    
    @Before
    public void setup() throws IOException{
        program = "ls";
        arguments = new String[]{"-l"};
        action = new ExternalProgramExecutionAction(program, arguments);
            
    }
    
    

    /**
     * Test of getDescription method, of class ExternalProgramExecutionAction.
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "Program Execution Action of:  " + program;
        assertEquals(expectedDescription, action.getDescription());
    }

    /**
     * Test of execute method, of class ExternalProgramExecutionAction.
     */
    @Test
    public void testExecute() {
        
    }
    
}
