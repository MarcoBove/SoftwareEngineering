/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author schet
 */
public class ExternalProgramExecutionActionTest {
    final String GITHUB_PROJECT_PATH = "Project_SE_Gruppo04";
    final String SAMPLE_FOLDER_NAME = "samples";
    final String LOCAL_PROJECT_PATH = System.getProperty("user.dir");
    final String APPLICATION_NAME = "a.exe";
    private ExternalProgramExecutionAction action;
    private String program;
    private String[] arguments;
    
    
    @Before
    public void setup() throws IOException{
        program = (new File(LOCAL_PROJECT_PATH).getParent())+ File.separator + GITHUB_PROJECT_PATH + File.separator + SAMPLE_FOLDER_NAME + File.separator + APPLICATION_NAME;
        arguments = new String[]{"casa"};
        action = new ExternalProgramExecutionAction(program, arguments);
            
    }
    
    @Test
    public void ExternalProgramExecutionAction(){
        ExternalProgramExecutionAction f=null;
        f = new ExternalProgramExecutionAction(new String("prova.txt"), new String[] {"ls"});
        assertNotNull(f);
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
    public void testExecute(){
       action.execute();
        assertEquals(0,action.getExitCode());
    }

    
    
}
