/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class CompositeActionTest {
    
    private static Action compositeAction;
    private static Action action1;
    private static Action action2;
    
    public CompositeActionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        compositeAction = new CompositeAction();
        action1 = new DisplayMessageAction("hi");
        action2 = new FileAppendAction("test Action",new File("samples/text.txt"));
    }
    
    @AfterClass
    public static void tearDownClass() {
        compositeAction.getAction().remove(0);
        compositeAction.getAction().remove(1);
    }
    
    @Before
    public void setUp() {
        compositeAction.addAction(action1);
        compositeAction.addAction(action2);
    }
    
    @After
    public void tearDown() {
        
        
       
    }
    
      @Test
    public void testGetDescription() {
        CompositeAction actions= new CompositeAction();
        actions.addAction(action1);
        actions.addAction(action2);
        String expectedDescription = "Action: [" + action1.getDescription() + ";\n" + action2.getDescription() + "]";
        assertEquals(expectedDescription, actions.getDescription()); 
    }

    /**
     * Test of addAction method, of class CompositeAction.
     */
    @Test
    public void testAddAction() {
        int dim = compositeAction.getAction().size();
        compositeAction.addAction(action1);
        assertTrue("Size with the added action is greater than before",compositeAction.getAction().size() > dim);
    }

  
    /**
     * Test of execute method, of class CompositeAction.
     */
    @Test
    public void testExecute() {
        compositeAction.execute();
    }

    /**
     * Test of getAction method, of class CompositeAction.
     */
    @Test
    public void testGetAction() {
         List<Action> actions = compositeAction.getAction();

        assert(actions instanceof ArrayList);
    }

    /**
     * Test of removeAction method, of class CompositeAction.
     */
    @Test
    public void testRemoveAction() {
        int dim = compositeAction.getAction().size();
        compositeAction.removeAction(action1);
        assertTrue("The size after removing an action is smaller than the previous size",compositeAction.getAction().size() < dim);
    }
    
}
