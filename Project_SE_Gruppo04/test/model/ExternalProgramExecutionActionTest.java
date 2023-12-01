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
    final String PERCORSO_PROGETTO_GITHUB = "Project_SE_Gruppo04";
    final String NOME_CARTELLA_SAMPLE = "sample";
    final String PERCORSO_PROGETTO_LOCALE = System.getProperty("user.dir");
    final String NOME_APPLICAZIONE = "a.exe";
    private ExternalProgramExecutionAction action;
    private String program;
    private String[] arguments;
    
    
    @Before
    public void setup() throws IOException{
        program = (new File(PERCORSO_PROGETTO_LOCALE).getParent())+ File.separator + PERCORSO_PROGETTO_GITHUB + File.separator + NOME_CARTELLA_SAMPLE + File.separator + NOME_APPLICAZIONE;
        arguments = new String[]{};
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
