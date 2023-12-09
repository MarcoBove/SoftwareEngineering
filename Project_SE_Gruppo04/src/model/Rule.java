/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author gruppo_04
 */
public class Rule implements Serializable {
    // Attributes
    private String name, description;
    private boolean enable;
    private Duration sleepingPeriod;
    private Action action;
    private Trigger trigger;
    private boolean fired;
    private LocalDateTime lastFired;
    
    // Constructor
    public Rule(String name, String description, Duration sleepingPeriod) {
        this.name = name;
        this.description = description;
        this.sleepingPeriod = sleepingPeriod;
        this.trigger=null;
        this.action=null;
        this.enable=true;
        this.fired=false;
        this.lastFired=null;
    }
    
    // Retrieve the timestamp of the most recent firing considering the sleeping period.
    public LocalDateTime getLastFired() {
        return lastFired;
    }

    // Set the timestamp for the last firing with consideration of the sleeping period.
    public void setLastFired(LocalDateTime lastFired) {
        this.lastFired = lastFired;
    }
    
    // Checks if the trigger associated with the rule is true
    public boolean checkTrigger(){
        
        return trigger.check();
    }
    
    // Executes the action associated with the rule
    public void executeAction(){
             action.execute();
    }
    
    // Method to activate the rule based on its enabling status, trigger, and action
    public void ruleActivation(){
        if(this.enable==true){
            
            if((this.fired == true)){
                if(this.hasTimePassed(LocalDateTime.now())){
                    this.fired = false;
                }
            }

            if(this.trigger == null || this.action == null || this.fired == true)
                return;

            if(this.checkTrigger()){
                this.fired=true;
                this.lastFired = LocalDateTime.now();
                this.executeAction();
            }
        }
    }
    
    // Getters and setters for various attributes
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Duration getSleepingPeriod() {
        return sleepingPeriod;
    }

    public void setSleepingPeriod(Duration sleepingPeriod) {
        this.sleepingPeriod = sleepingPeriod;
    }

    public Action getAction() {
        return action;
    }
   
    public void setAction(Action action) {
        this.action = action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
      
    }

    // to string of the rule
    @Override
    public String toString() {
        return name;
    }
    
    // Checks if a specified time has passed based on the rule's sleeping period
    public boolean hasTimePassed(LocalDateTime now){
        if(!this.sleepingPeriod.equals(Duration.ZERO)){
            return (now.isAfter(this.lastFired.plus(this.sleepingPeriod)));
        }
        return false;
    }
}
