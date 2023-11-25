/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.Duration;
import java.util.Objects;

/**
 *
 * @author Andre
 */
public class Rule {
    private String name, description;
    private boolean enable;
    private Duration sleepingPeriod;
    private Action action;
    private Trigger trigger;
    private boolean fired;
    
    

    public Rule(String name, String description, Duration sleepingPeriod) {
        this.name = name;
        this.description = description;
        this.sleepingPeriod = sleepingPeriod;
        this.trigger=null;
        this.action=null;
        this.enable=true;
        this.fired=false;
    }

    
    
    public boolean checkTrigger(){
        
        return trigger.check();
    }
    
    public void executeAction(){
             action.execute();
    }
    
    public void ruleActivation(){ 
        if(this.trigger == null || this.action == null || fired == true)
            return; 
        if(this.checkTrigger()){
            this.executeAction();
            fired=true;
            
        }
        
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rule other = (Rule) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
