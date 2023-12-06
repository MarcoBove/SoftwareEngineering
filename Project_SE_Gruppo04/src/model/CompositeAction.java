/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 39327
 */
public class CompositeAction implements Action {
    
    private List<Action> actions;

    public CompositeAction() {
        this.actions = new ArrayList<>();
    }
    
    @Override
    public void addAction(Action action) {
        actions.add(action);
    }
    
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

    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    @Override
    public void log(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Action> getAction() {
        return actions; 
    }

    @Override
    public void removeAction(Action actionToRemove) {
        actions.remove(actionToRemove);
    }
    
}
