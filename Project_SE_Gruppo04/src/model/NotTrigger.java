/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gruppo_04
 */
public class NotTrigger implements Trigger {

    Trigger trigger;

    public NotTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    
    //Checks the logical NOT of the encapsulated Trigger's check.
    @Override
    public boolean check() {
        return !trigger.check();
    }

    // Provides a description of the NOT operation for the encapsulated Trigger's description.
    @Override
    public String getDescription() {
        return " (NOT " + trigger.getDescription() + ")";
    }
    
    //Only for composite triggers.
    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
