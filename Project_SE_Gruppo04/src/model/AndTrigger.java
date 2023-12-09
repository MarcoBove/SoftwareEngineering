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
public class AndTrigger implements Trigger{
    
    private List<Trigger> triggers;

    // Adds a Trigger to the list of Triggers.
    public AndTrigger() {
        this.triggers = new ArrayList<>();
    }
    
    public void addTrigger(Trigger t){
        triggers.add(t);        
    }
    
    // Checks the logical AND of all Triggers in the list.
    @Override
    public boolean check() {
       return triggers.stream().allMatch(Trigger::check);         //Returns true if all triggers are true; otherwise, false.
    }

    // Provides a description of the AND operation for the list of Triggers.
    @Override
    public String getDescription() {
       StringBuilder description = new StringBuilder("(");
        for (int i = 0; i < triggers.size(); i++) {
            description.append(triggers.get(i).getDescription());
            if(i<triggers.size()-1)
                description.append(" AND ");
        }
        description.append(")");
        return description.toString();
    }      
}
