/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Andre
 */
public class NotTrigger implements Trigger {

    Trigger trigger;

    public NotTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    
    @Override
    public boolean check() {
        return !trigger.check();
    }

    @Override
    public String getDescription() {
        return " (NOT " + trigger.getDescription() + ")";
    }

    @Override
    public void addTrigger(Trigger t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
