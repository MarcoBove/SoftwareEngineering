/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gruppo_04
 */
public class OrTrigger implements Trigger{
    
    private List<Trigger> triggers;

    public OrTrigger() {
        this.triggers = new ArrayList<>();
    }
    
    // Adds a Trigger to the list of Triggers
    public void addTrigger(Trigger t){
        triggers.add(t);        
    }
    
    // Checks the logical OR of all Triggers in the list.
    @Override
    public boolean check() {
       return triggers.stream().anyMatch(Trigger::check);         //Returns true if at least one trigger is true; otherwise, false.
    }
    
    // Provides a description of the OR operation for the list of Triggers.
    @Override
    public String getDescription() {
       StringBuilder description = new StringBuilder("(");
        for (int i = 0; i < triggers.size(); i++) {
            description.append(triggers.get(i).getDescription());
             if(i<triggers.size()-1)
                description.append(" OR ");
        }
        description.append(")");
        return description.toString();
    }    
    
    // Returns the list of triggers
    public List<Trigger> getTriggers(){
        return triggers;
    }
    
}
