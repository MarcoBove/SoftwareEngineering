/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author gruppo_04
 */
public class CompositeActionTest {
    
    // Test variables
    private static Action compositeAction;
    private static Action action1;
    private static Action action2;
    private static File file;
    
    // Initializes CompositeAction parameters for testing
    @BeforeClass
    public static void setUpClass() {
        file= new File("samples/text.txt");
        compositeAction = new CompositeAction();
        action1 = new DisplayMessageAction("hi");
        action2 = new FileAppendAction("test Action",file);
    }
    
     @AfterClass
    public static void tearDownClass() {
        compositeAction.getAction().remove(0);
        compositeAction.getAction().remove(1);
        file.delete();
    }
    
    @Before
    public void setUp() {
        compositeAction.addAction(action1);
        compositeAction.addAction(action2);
    }
    
      @Test
    public void testGetDescription() {
        CompositeAction actions= new CompositeAction();
        actions.addAction(action1);
        actions.addAction(action2);
        String expectedDescription = "Actions: [" + action1.getDescription() + ";\n" + action2.getDescription() + "]";
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
     This method iterates through the list of actions and calls the execute method on each action.
     * Since we test the execute method in the respective test classes for each action, the method is already tested
    @Test
    public void testExecute() {
        compositeAction.execute();
    }
*/
    
    /**
     * Test of getAction method, of class CompositeAction.
     */
    @Test
    public void testGetAction() {
         List<Action> actions = compositeAction.getAction();
         assertTrue(actions.contains(action1));
         assertTrue(actions.contains(action2));
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
    
    @Test (expected = UnsupportedOperationException.class)
    public void testLog(){
        compositeAction.log("");
    }
    
}
