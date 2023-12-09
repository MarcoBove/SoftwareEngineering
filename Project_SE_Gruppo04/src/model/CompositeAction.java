/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
@author gruppo_04
 */
public class CompositeAction implements Action {
    
    private List<Action> actions;

    
    public CompositeAction() {
        this.actions = new ArrayList<>();
    }
    
    // adds the action to the list
    @Override
    public void addAction(Action action) {
        actions.add(action);
    }
    
    // returns the description of all the actions that compose the list
    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder("Action: [");
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            description.append(action.getDescription());
            if (i < actions.size() - 1) {
                description.append(";\n");
            }
        }
        description.append("]");
        return description.toString();
    }

    // executes the actions in sequence
    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    //not implemented method
    @Override
    public void log(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    // returns the list of actions
    @Override
    public List<Action> getAction() {
        return actions; 
    }

    // removes the action from the list of actions
    @Override
    public void removeAction(Action actionToRemove) {
        actions.remove(actionToRemove);
    }
    
}
